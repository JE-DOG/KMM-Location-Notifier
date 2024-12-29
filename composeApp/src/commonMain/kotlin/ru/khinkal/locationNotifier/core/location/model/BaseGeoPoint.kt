package ru.khinkal.locationNotifier.core.location.model

import ru.khinkal.locationNotifier.feature.locationList.domain.model.GeoPoint

data class BaseGeoPoint(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
) {
    constructor(geoPoint: GeoPoint) : this(geoPoint.latitude, geoPoint.longitude)
}
