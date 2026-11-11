package app.organicmaps.wear

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.wear.ongoing.OngoingActivity
import app.organicmaps.wear.protocol.WearNavigationData
import app.organicmaps.wear.protocol.WearNavigationDetails
import app.organicmaps.wear.protocol.WearNavigationMode
import app.organicmaps.wear.protocol.gms.WearNavigationDataMapCodec
import app.organicmaps.wear.protocol.gms.WearNavigationDetailsDataMapCodec
import com.google.android.gms.wearable.CapabilityClient
import com.google.android.gms.wearable.CapabilityInfo
import com.google.android.gms.wearable.DataClient
import com.google.android.gms.wearable.DataEventBuffer
import com.google.android.gms.wearable.DataItemBuffer
import com.google.android.gms.wearable.DataMap
import com.google.android.gms.wearable.DataMapItem
import com.google.android.gms.wearable.MessageClient
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.Node
import com.google.android.gms.wearable.PutDataRequest
import com.google.android.gms.wearable.Wearable

/**
 * Keeps the watch discoverable while phone navigation is active and the screen is not resumed.
 * Started from the visible Activity: background starts have additional platform restrictions.
 */
class WearNavigationService :
    Service(),
    DataClient.OnDataChangedListener,
    MessageClient.OnMessageReceivedListener,
    CapabilityClient.OnCapabilityChangedListener {
    private lateinit var dataClient: DataClient
    private lateinit var messageClient: MessageClient
    private lateinit var capabilityClient: CapabilityClient
    private lateinit var capabilityOwner: ActiveWearUiCapabilityOwner

    private val state = WearNavigationServiceState()
    private val handler = Handler(Looper.getMainLooper())
    private val messageListenerRetry = Runnable { registerMessageListener(state.generation) }
    private var refreshGeneration = 0
    private var started = false
    private var displayedStreet: String? = null

    override fun onCreate() {
        super.onCreate()
        dataClient = Wearable.getDataClient(this)
        messageClient = Wearable.getMessageClient(this)
        capabilityClient = Wearable.getCapabilityClient(this)
        capabilityOwner = ActiveWearUiCapabilityRegistry.get(applicationContext)
        createNotificationChannel()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (!started) {
            started = true
            // Must happen promptly after startForegroundService(), before asynchronous reads.
            startForeground(NOTIFICATION_ID, buildNotification())
            registerStateListener()
            registerPhoneCapabilityListener()
        }
        refreshNavigationState()
        return START_NOT_STICKY
    }

    private fun registerMessageListener(generation: Int) {
        if (!started || generation != state.generation) return

        messageClient.addListener(this)
            .addOnSuccessListener {
                if (started && state.listenerRegistered(generation)) {
                    handler.removeCallbacks(messageListenerRetry)
                    capabilityOwner.activate(this)
                } else if (!started) {
                    messageClient.removeListener(this)
                }
            }.addOnFailureListener { exception ->
                Log.w(TAG, "Failed to listen for Wear navigation details", exception)
                if (started && state.listenerRegistrationFailed(generation)) {
                    handler.removeCallbacks(messageListenerRetry)
                    handler.postDelayed(messageListenerRetry, MESSAGE_LISTENER_RETRY_DELAY_MS)
                }
            }
    }

    private fun registerStateListener() {
        dataClient.addListener(this, NAVIGATION_STATE_URI, DataClient.FILTER_LITERAL)
            .addOnSuccessListener {
                if (!started) dataClient.removeListener(this)
            }.addOnFailureListener { exception ->
                Log.w(TAG, "Failed to listen for phone navigation state", exception)
                if (started) stopSelf()
            }
    }

    private fun registerPhoneCapabilityListener() {
        capabilityClient.addListener(this, WearNavigationData.CAPABILITY_PHONE_APP)
            .addOnSuccessListener {
                if (!started) {
                    capabilityClient.removeListener(this, WearNavigationData.CAPABILITY_PHONE_APP)
                } else {
                    refreshNavigationState()
                }
            }.addOnFailureListener { exception ->
                Log.w(TAG, "Failed to listen for phone capability", exception)
                if (started) stopSelf()
            }
    }

    override fun onDataChanged(dataEvents: DataEventBuffer) {
        if (started && NavigationStateSource.requiresRefresh(dataEvents)) refreshNavigationState()
    }

    override fun onCapabilityChanged(capability: CapabilityInfo) {
        if (started && capability.name == WearNavigationData.CAPABILITY_PHONE_APP) {
            refreshNavigationState()
        }
    }

    override fun onMessageReceived(messageEvent: MessageEvent) {
        if (!started || messageEvent.path != WearNavigationData.PATH_NAVIGATION_DETAILS) return

        val details: WearNavigationDetails? = try {
            WearNavigationDetailsDataMapCodec.decode(DataMap.fromByteArray(messageEvent.data))
        } catch (exception: IllegalArgumentException) {
            Log.w(TAG, "Failed to decode Wear navigation details", exception)
            null
        }

        if (details != null) {
            val nextStreet = details.nextStreet?.takeIf { it.isNotBlank() }
            if (displayedStreet != nextStreet) {
                displayedStreet = nextStreet
                getSystemService(NotificationManager::class.java)
                    .notify(NOTIFICATION_ID, buildNotification())
            }
        }
    }

    private fun refreshNavigationState() {
        if (!started) return
        val generation = ++refreshGeneration

        capabilityClient.getCapability(
            WearNavigationData.CAPABILITY_PHONE_APP,
            CapabilityClient.FILTER_REACHABLE,
        ).addOnSuccessListener { capability ->
            if (!isCurrent(generation)) return@addOnSuccessListener
            val companion = NavigationStateSource.selectCompanion(capability.nodes)
            if (companion == null) {
                stopSelf()
            } else {
                readNavigationState(companion, generation)
            }
        }.addOnFailureListener { exception ->
            Log.w(TAG, "Failed to find the companion phone", exception)
            if (isCurrent(generation)) stopSelf()
        }
    }

    private fun readNavigationState(companion: Node, generation: Int) {
        dataClient.getDataItems(navigationStateUri(companion.id), DataClient.FILTER_LITERAL)
            .addOnSuccessListener { items -> readMode(items, generation) }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Failed to read navigation mode", exception)
                if (isCurrent(generation)) stopSelf()
            }
    }

    private fun readMode(items: DataItemBuffer, generation: Int) {
        val mode = try {
            if (items.count == 1) {
                WearNavigationDataMapCodec.decode(DataMapItem.fromDataItem(items[0]).dataMap)
            } else {
                null
            }
        } catch (exception: IllegalStateException) {
            Log.w(TAG, "Failed to decode navigation mode", exception)
            null
        } finally {
            items.release()
        }

        if (!isCurrent(generation)) return
        if (mode != WearNavigationMode.NAVIGATION) {
            stopSelf()
        } else if (state.mode != WearNavigationMode.NAVIGATION) {
            // Do not advertise the watch capability until the phone's mode is verified.
            registerMessageListener(state.updateMode(WearNavigationMode.NAVIGATION))
        }
    }

    private fun isCurrent(generation: Int): Boolean = started && generation == refreshGeneration

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            getString(R.string.app_name),
            NotificationManager.IMPORTANCE_LOW,
        )
        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
    }

    private fun buildNotification(): Notification {
        val openApp = PendingIntent.getActivity(
            this,
            0,
            Intent(this, MainActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_wear_navigation)
            .setContentTitle(getString(R.string.app_name))
            .setContentText(displayedStreet ?: getString(R.string.wear_navigation_active_message))
            .setCategory(NotificationCompat.CATEGORY_NAVIGATION)
            .setContentIntent(openApp)
            .setOnlyAlertOnce(true)
            .setOngoing(true)

        OngoingActivity.Builder(this, NOTIFICATION_ID, notificationBuilder)
            .setStaticIcon(R.drawable.ic_wear_navigation)
            .setTouchIntent(openApp)
            .build()
            .apply(this)
        return notificationBuilder.build()
    }

    override fun onDestroy() {
        started = false
        state.stop()
        refreshGeneration++
        handler.removeCallbacks(messageListenerRetry)
        capabilityOwner.deactivate(this)
        dataClient.removeListener(this)
        messageClient.removeListener(this)
        capabilityClient.removeListener(this, WearNavigationData.CAPABILITY_PHONE_APP)
        stopForeground(STOP_FOREGROUND_REMOVE)
        super.onDestroy()
    }

    companion object {
        private const val TAG = "WearNavigationService"
        private const val CHANNEL_ID = "wear_navigation"
        private const val NOTIFICATION_ID = 14115
        private const val MESSAGE_LISTENER_RETRY_DELAY_MS = 2_000L
        private val NAVIGATION_STATE_URI = navigationStateUri("*")

        private fun navigationStateUri(authority: String): Uri = Uri.Builder()
            .scheme(PutDataRequest.WEAR_URI_SCHEME)
            .authority(authority)
            .path(WearNavigationData.PATH_NAVIGATION_STATE)
            .build()

        fun start(context: Context) {
            context.startForegroundService(Intent(context, WearNavigationService::class.java))
        }

        fun stop(context: Context) {
            context.stopService(Intent(context, WearNavigationService::class.java))
        }
    }
}
