package ru.khinkal.locationNotifier.feature.locationList.presentation.vm.model

import ru.khinkal.locationNotifier.feature.locationList.domain.model.GeoPoint

sealed interface LocationListAction {

    sealed interface NavigateTo : LocationListAction {

        object Settings : NavigateTo
        object SetGeoPoint : NavigateTo
    }

    object ChangeIsAdd : LocationListAction

    data class BroadcastLocation(val geoPoint: GeoPoint) : LocationListAction
}