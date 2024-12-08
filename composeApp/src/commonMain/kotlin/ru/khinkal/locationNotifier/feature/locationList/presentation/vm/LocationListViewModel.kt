package ru.khinkal.locationNotifier.feature.locationList.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
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

    private fun onChangeIsAdd() {
        _state.update { state ->
            state.copy(
                isAdd = !state.isAdd,
            )
        }
    }
}