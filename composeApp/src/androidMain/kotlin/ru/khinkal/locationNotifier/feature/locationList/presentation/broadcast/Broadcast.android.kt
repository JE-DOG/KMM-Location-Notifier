package ru.khinkal.locationNotifier.feature.locationList.presentation.broadcast

import ru.khinkal.locationNotifier.LocationNotifierApp
import ru.khinkal.locationNotifier.feature.locationList.domain.model.GeoPoint

actual fun startBroadcast(
    geoPoint: GeoPoint,
    locationUpdateSecondsInterval: Int,
    vibrationState: Boolean
) {
    val application = LocationNotifierApp.INSTANCE

    BroadcastLocationService.startBroadcast(
        context = application,
        geoPoint = geoPoint,
        locationUpdateSecondsInterval = locationUpdateSecondsInterval,
        vibrationState = vibrationState,
    )
}