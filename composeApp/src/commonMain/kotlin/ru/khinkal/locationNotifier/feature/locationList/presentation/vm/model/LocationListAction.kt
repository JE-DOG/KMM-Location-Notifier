package ru.khinkal.locationNotifier.feature.locationList.presentation.vm.model

sealed interface LocationListAction {

    sealed interface NavigateTo : LocationListAction {

        object Settings : NavigateTo
        object SetGeoPoint : NavigateTo
    }

    object ChangeIsAdd : LocationListAction
}