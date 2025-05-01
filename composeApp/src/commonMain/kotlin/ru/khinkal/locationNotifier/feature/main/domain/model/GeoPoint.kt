package ru.khinkal.locationNotifier.feature.main.domain.model

import kotlinx.serialization.Serializable

// TODO: LN-20 - Rebuild geoPoint structure(Tech-debt iteration 2)
@Serializable
data class GeoPoint(
    val id: Int = 0,
    val name: String = "",
    val meters: Int = 0,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
)
