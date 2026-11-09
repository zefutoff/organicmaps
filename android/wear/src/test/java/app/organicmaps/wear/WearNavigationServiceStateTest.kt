package app.organicmaps.wear

import app.organicmaps.wear.protocol.WearNavigationMode
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class WearNavigationServiceStateTest {
    @Test
    fun capabilityIsNotAdvertisedUntilListenerIsReady() {
        val state = WearNavigationServiceState()
        val generation = state.updateMode(WearNavigationMode.NAVIGATION)
        assertTrue(state.shouldShowOngoingActivity)
        assertFalse(state.shouldAdvertiseCapability)
        assertTrue(state.listenerRegistered(generation))
        assertTrue(state.shouldAdvertiseCapability)
    }

    @Test
    fun lateRegistrationAfterStopCannotAdvertiseCapability() {
        val state = WearNavigationServiceState()
        val generation = state.updateMode(WearNavigationMode.NAVIGATION)
        state.stop()
        assertFalse(state.listenerRegistered(generation))
        assertFalse(state.shouldAdvertiseCapability)
        assertFalse(state.shouldShowOngoingActivity)
    }

    @Test
    fun lateRegistrationFromPreviousSessionIsIgnored() {
        val state = WearNavigationServiceState()
        val oldGeneration = state.updateMode(WearNavigationMode.NAVIGATION)
        state.updateMode(WearNavigationMode.NORMAL)
        val newGeneration = state.updateMode(WearNavigationMode.NAVIGATION)
        assertFalse(state.listenerRegistered(oldGeneration))
        assertFalse(state.shouldAdvertiseCapability)
        assertTrue(state.listenerRegistered(newGeneration))
        assertTrue(state.shouldAdvertiseCapability)
    }

    @Test
    fun listenerFailureKeepsCapabilityInactive() {
        val state = WearNavigationServiceState()
        val generation = state.updateMode(WearNavigationMode.NAVIGATION)
        assertTrue(state.listenerRegistrationFailed(generation))
        assertFalse(state.shouldAdvertiseCapability)
        assertTrue(state.listenerRegistered(generation))
        assertTrue(state.shouldAdvertiseCapability)
    }
}
