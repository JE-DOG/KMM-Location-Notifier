package ru.khinkal.locationNotifier.core.location.model

import kotlinx.serialization.Serializable

@Serializable
data class GeoPoint(
    val latitude: Double,
    val longitude: Double,
)
