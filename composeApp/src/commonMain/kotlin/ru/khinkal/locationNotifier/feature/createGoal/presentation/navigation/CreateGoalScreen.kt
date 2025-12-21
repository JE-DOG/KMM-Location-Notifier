package ru.khinkal.locationNotifier.feature.createGoal.presentation.navigation

import kotlinx.serialization.Serializable
import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint

@Serializable
data class CreateGoalScreen(
    val geoPoint: GoalGeoPoint? = null,
)
