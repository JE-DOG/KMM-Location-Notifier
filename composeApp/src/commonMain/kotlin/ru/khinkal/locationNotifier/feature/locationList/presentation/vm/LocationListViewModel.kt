package ru.khinkal.locationNotifier.feature.locationList.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import ru.khinkal.locationNotifier.feature.locationList.domain.model.GeoPoint
import ru.khinkal.locationNotifier.feature.locationList.presentation.broadcast.startBroadcast
import ru.khinkal.locationNotifier.feature.locationList.presentation.vm.model.LocationListAction
import ru.khinkal.locationNotifier.feature.locationList.presentation.vm.model.LocationListState
import ru.khinkal.locationNotifier.feature.setGeoPoint.navigation.SetGeoPointScreen
import ru.khinkal.locationNotifier.feature.settings.navigation.SettingsScreen

class LocationListViewModel(
    private val navController: NavController,
): ViewModel() {

    private val _state: MutableStateFlow<LocationListState> = MutableStateFlow(LocationListState())
    val state: StateFlow<LocationListState> get() = _state

    fun action(action: LocationListAction) {
        when (action) {
            LocationListAction.ChangeIsAdd -> onChangeIsAdd()
            is LocationListAction.NavigateTo -> onNavigateToAction(action)
            is LocationListAction.BroadcastLocation -> onBroadcastLocation(geoPoint = action.geoPoint)
        }
    }

    private fun onNavigateToAction(action: LocationListAction.NavigateTo) {
        when (action) {
            LocationListAction.NavigateTo.SetGeoPoint -> navigateToSetGeoPoint()
            LocationListAction.NavigateTo.Settings -> navigateToSettings()
        }
    }

    private fun navigateToSetGeoPoint() {
        navController.navigate(SetGeoPointScreen)
    }

    private fun navigateToSettings() {
        navController.navigate(SettingsScreen)
    }

    private fun onBroadcastLocation(geoPoint: GeoPoint) {
        //TODO MAKE IT BETTER
        startBroadcast(
            geoPoint = geoPoint,
        )
    }

    private fun onChangeIsAdd() {
        _state.update { state ->
            state.copy(
                isAdd = !state.isAdd,
            )
        }
    }
}