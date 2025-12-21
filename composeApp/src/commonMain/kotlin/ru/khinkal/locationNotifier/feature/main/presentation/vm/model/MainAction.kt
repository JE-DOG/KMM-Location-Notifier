package ru.khinkal.locationNotifier.feature.main.presentation.vm.model

import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint

sealed interface MainAction {

    data object OnSettingsClick : MainAction

    class OnLocationClick(val goalGeoPoint: GoalGeoPoint) : MainAction
    class OnEditLocationClick(val goalGeoPoint: GoalGeoPoint) : MainAction
    class OnDeleteLocationClick(val goalGeoPoint: GoalGeoPoint) : MainAction

    data object OnAddLocationClick : MainAction
}
