package ru.khinkal.locationNotifier.core.location.model

import kotlinx.serialization.Serializable
import ru.khinkal.locationNotifier.feature.locationList.domain.model.GeoPoint

@Serializable
data class BaseGeoPoint(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
) {
    constructor(geoPoint: GeoPoint) : this(geoPoint.latitude, geoPoint.longitude)
}
