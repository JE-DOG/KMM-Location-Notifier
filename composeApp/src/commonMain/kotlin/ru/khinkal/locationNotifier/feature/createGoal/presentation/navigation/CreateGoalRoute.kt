package ru.khinkal.locationNotifier.feature.createGoal.presentation.navigation

import kotlinx.serialization.Serializable
import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint
import ru.khinkal.locationNotifier.navigation.NavRoute

@Serializable
data class CreateGoalRoute(
    val geoPoint: GoalGeoPoint? = null,
) : NavRoute()
