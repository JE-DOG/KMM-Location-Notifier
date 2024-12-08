package ru.khinkal.locationNotifier.feature.locationList.domain.model

data class GeoPoint(
    val id: Int = 0,
    val name: String = "",
    val meters: Int = 0,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
)
