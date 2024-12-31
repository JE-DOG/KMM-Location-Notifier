package ru.khinkal.locationNotifier.feature.locationList.presentation.vm.model

import ru.khinkal.locationNotifier.feature.locationList.domain.model.GeoPoint

sealed interface LocationListAction {

    sealed interface NavigateTo : LocationListAction {

        data object Settings : NavigateTo
        data object CreateGoal : NavigateTo
    }

    data class BroadcastLocation(val geoPoint: GeoPoint) : LocationListAction
}