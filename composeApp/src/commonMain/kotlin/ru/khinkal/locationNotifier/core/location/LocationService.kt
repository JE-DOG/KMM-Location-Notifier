package ru.khinkal.locationNotifier.core.location

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import ru.khinkal.locationNotifier.core.location.model.GeoPoint

abstract class LocationService(
    coroutineScope: CoroutineScope,
) {

    private val _geoPoint = MutableStateFlow<GeoPoint?>(null)
    val geoPoint: StateFlow<GeoPoint?> = _geoPoint
        .onStart { observeUserGeoPoint() }
        .onCompletion { stopBroadcast() }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(
                stopTimeoutMillis = 5_000L,
            ),
            initialValue = _geoPoint.value,
        )

    private fun observeUserGeoPoint() {
        startBroadcast()
    }

    protected fun updateLocation(geoPoint: GeoPoint) {
        _geoPoint.update { geoPoint }
    }

    protected abstract fun startBroadcast()

    protected abstract fun stopBroadcast()

}
