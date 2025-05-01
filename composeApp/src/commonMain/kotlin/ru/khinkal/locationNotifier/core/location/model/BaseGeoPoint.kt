package ru.khinkal.locationNotifier.core.location.model

import kotlinx.serialization.Serializable
import ru.khinkal.locationNotifier.feature.main.domain.model.GeoPoint

// TODO: LN-20 - Rebuild geoPoint structure(Tech-debt iteration 2)
@Serializable
data class BaseGeoPoint(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
) {
    constructor(geoPoint: GeoPoint) : this(
        latitude = geoPoint.latitude,
        longitude = geoPoint.longitude,
    )
}
