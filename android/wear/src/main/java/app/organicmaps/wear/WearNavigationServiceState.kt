package app.organicmaps.wear

import app.organicmaps.wear.protocol.WearNavigationMode

/**
 * Tracks the watch-side service session independently from Android callbacks.
 * A generation invalidates asynchronous completions from previous sessions.
 * The service owns the capability only after MessageClient registration succeeds.
 */
internal class WearNavigationServiceState {
    var mode = WearNavigationMode.NORMAL
        private set

    var generation = 0
        private set

    var listenerReady = false
        private set

    val shouldAdvertiseCapability: Boolean
        get() = mode == WearNavigationMode.NAVIGATION && listenerReady

    val shouldShowOngoingActivity: Boolean
        get() = mode == WearNavigationMode.NAVIGATION

    fun updateMode(newMode: WearNavigationMode): Int {
        if (newMode != mode) {
            mode = newMode
            generation++
            listenerReady = false
        }
        return generation
    }

    fun listenerRegistered(expectedGeneration: Int): Boolean {
        if (expectedGeneration != generation || mode != WearNavigationMode.NAVIGATION) return false
        listenerReady = true
        return true
    }

    fun listenerRegistrationFailed(expectedGeneration: Int): Boolean {
        if (expectedGeneration != generation || mode != WearNavigationMode.NAVIGATION) return false
        listenerReady = false
        return true
    }

    fun stop(): Int {
        mode = WearNavigationMode.NORMAL
        listenerReady = false
        return ++generation
    }
}
