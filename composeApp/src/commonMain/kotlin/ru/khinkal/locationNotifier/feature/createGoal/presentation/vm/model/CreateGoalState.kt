package ru.khinkal.locationNotifier.feature.createGoal.presentation.vm.model

import ru.khinkal.locationNotifier.core.location.model.BaseGeoPoint

data class CreateGoalState(
    val name: String = "",
    val meters: Int? = null,
    val baseGeoPoint: BaseGeoPoint? = null,
)
