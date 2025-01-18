package ru.khinkal.locationNotifier.feature.locationList.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import ru.khinkal.locationNotifier.core.location.utill.observeResult
import ru.khinkal.locationNotifier.feature.createGoal.presentation.navigation.CreateGoalScreen
import ru.khinkal.locationNotifier.feature.locationList.domain.model.GeoPoint
import ru.khinkal.locationNotifier.feature.locationList.presentation.broadcast.startBroadcast
import ru.khinkal.locationNotifier.feature.locationList.presentation.vm.model.LocationListAction
import ru.khinkal.locationNotifier.feature.locationList.presentation.vm.model.LocationListState
import ru.khinkal.locationNotifier.feature.setGeoPoint.navigation.SetGeoPointScreen
import ru.khinkal.locationNotifier.feature.settings.navigation.SettingsScreen
import ru.khinkal.locationNotifier.shared.navigation.ResultKeys

class LocationListViewModel(
    private val navController: NavController,
): ViewModel() {

    private val _state: MutableStateFlow<LocationListState> = MutableStateFlow(LocationListState())
    val state: StateFlow<LocationListState> get() = _state

    init {
        observeCreatedLocation()
    }

    fun action(action: LocationListAction) {
        when (action) {
            is LocationListAction.NavigateTo -> onNavigateToAction(action)
            is LocationListAction.BroadcastLocation -> onBroadcastLocation(geoPoint = action.geoPoint)
        }
    }

    private fun onNavigateToAction(action: LocationListAction.NavigateTo) {
        when (action) {
            LocationListAction.NavigateTo.Settings -> navigateToSettings()
            LocationListAction.NavigateTo.CreateGoal -> navigateToCreateGoal()
            LocationListAction.NavigateTo.SetGeoPoint -> navigateToSetGeoPoint()
        }
    }

    private fun navigateToSettings() {
        navController.navigate(SettingsScreen)
    }

    private fun navigateToCreateGoal() {
        navController.navigate(CreateGoalScreen)
    }

    private fun navigateToSetGeoPoint() {
        navController.navigate(SetGeoPointScreen)
    }

    private fun onBroadcastLocation(geoPoint: GeoPoint) {
        //TODO MAKE IT BETTER
        startBroadcast(
            geoPoint = geoPoint,
        )
    }

    private fun observeCreatedLocation() {
        viewModelScope.launch {
            navController
                .observeResult<String?>(ResultKeys.GOAL_CREATED, null)
                ?.collect { geoPointJson ->
                    if (geoPointJson == null) return@collect
                    val geoPoint = Json.decodeFromString<GeoPoint>(geoPointJson)
                    startBroadcast(
                        geoPoint = geoPoint,
                    )
                }
        }
    }
}