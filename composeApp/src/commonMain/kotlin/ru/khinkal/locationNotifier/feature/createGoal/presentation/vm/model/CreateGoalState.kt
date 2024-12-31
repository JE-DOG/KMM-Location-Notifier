package ru.khinkal.locationNotifier.feature.createGoal.presentation.vm.model

data class CreateGoalState(
    val name: String = "",
    val meters: Int? = null,
    val longitude: Double? = null,
    val latitude: Double? = null,
)
