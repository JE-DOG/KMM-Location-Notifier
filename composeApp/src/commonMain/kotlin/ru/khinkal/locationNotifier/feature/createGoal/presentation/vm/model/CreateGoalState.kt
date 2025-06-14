package ru.khinkal.locationNotifier.feature.createGoal.presentation.vm.model

import ru.khinkal.locationNotifier.core.location.model.GeoPoint

data class CreateGoalState(
    val name: String = "",
    val meters: Int? = null,
    val geoPoint: GeoPoint? = null,
)
