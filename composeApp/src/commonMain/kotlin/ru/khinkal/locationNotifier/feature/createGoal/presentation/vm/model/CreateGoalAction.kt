package ru.khinkal.locationNotifier.feature.createGoal.presentation.vm.model

sealed interface CreateGoalAction {

    data object Back : CreateGoalAction

    sealed interface SetProperty : CreateGoalAction {

        data class SetName(val name: String) : SetProperty

        data class SetMeters(val meters: Int?) : SetProperty

        data class SetLongitude(val longitude: Double?) : SetProperty

        data class SetLatitude(val latitude: Double?) : SetProperty
    }

    data object StartBroadcast : CreateGoalAction
}