package ru.khinkal.locationNotifier.feature.locationList.presentation.vm.model

import ru.khinkal.locationNotifier.feature.locationList.domain.model.GeoPoint

sealed interface LocationListAction {

    class OnSettingsClick : LocationListAction

    class OnLocationClick(val geoPoint: GeoPoint) : LocationListAction

    class OnAddLocationClick : LocationListAction
}