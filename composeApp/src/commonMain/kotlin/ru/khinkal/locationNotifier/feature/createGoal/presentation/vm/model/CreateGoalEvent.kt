package ru.khinkal.locationNotifier.feature.createGoal.presentation.vm.model

import ru.khinkal.locationNotifier.navigation.NavRoute

sealed interface CreateGoalEvent {

    data class NavigateTo(val route: NavRoute) : CreateGoalEvent

    data object NavigateBack : CreateGoalEvent
}
