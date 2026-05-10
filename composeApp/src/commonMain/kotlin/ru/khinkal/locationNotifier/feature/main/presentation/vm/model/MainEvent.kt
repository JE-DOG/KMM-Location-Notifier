package ru.khinkal.locationNotifier.feature.main.presentation.vm.model

import ru.khinkal.locationNotifier.navigation.NavRoute

sealed interface MainEvent {

    data class NavigateTo(val route: NavRoute) : MainEvent
}
