package ru.khinkal.locationNotifier.feature.main.domain.model

import kotlinx.serialization.Serializable
import ru.khinkal.locationNotifier.core.location.model.GeoPoint

@Serializable
data class GoalGeoPoint(
    val id: Int = 0,
    val name: String = "",
    val meters: Int = 0,
    val geoPoint: GeoPoint,
)
