package ru.khinkal.locationNotifier.feature.locationList.presentation.broadcast

import ru.khinkal.locationNotifier.feature.locationList.domain.model.GeoPoint

expect fun startBroadcast(
    geoPoint: GeoPoint,
    locationUpdateSecondsInterval: Int = 5,
    vibrationState: Boolean = true,
)