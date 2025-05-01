package ru.khinkal.locationNotifier.feature.main.presentation.vm.model

import ru.khinkal.locationNotifier.feature.main.domain.model.GeoPoint

sealed interface MainAction {

    data object OnSettingsClick : MainAction

    class OnLocationClick(val geoPoint: GeoPoint) : MainAction

    data object OnAddLocationClick : MainAction
}
