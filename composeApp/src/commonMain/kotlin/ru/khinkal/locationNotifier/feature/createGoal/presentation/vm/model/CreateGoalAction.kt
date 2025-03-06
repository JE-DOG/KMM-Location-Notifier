package ru.khinkal.locationNotifier.feature.createGoal.presentation.vm.model

sealed interface CreateGoalAction {

    data object GoBack : CreateGoalAction

    sealed interface SetProperty : CreateGoalAction {

        data class SetName(val name: String) : SetProperty

        data class SetMeters(val meters: Int?) : SetProperty
    }

    data object OnSetBaseGeoPointClicked : CreateGoalAction

    data object StartBroadcast : CreateGoalAction
}