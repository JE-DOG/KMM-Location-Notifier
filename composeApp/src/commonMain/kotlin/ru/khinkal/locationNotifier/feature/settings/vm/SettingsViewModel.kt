package ru.khinkal.locationNotifier.feature.settings.vm

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.khinkal.locationNotifier.feature.settings.vm.model.SettingsAction
import ru.khinkal.locationNotifier.feature.settings.vm.model.SettingsUiState

class SettingsViewModel(
    private val navController: NavController,
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsUiState.EMPTY)
    val state: StateFlow<SettingsUiState> get() = _state.asStateFlow()

    fun onAction(action: SettingsAction) {
        when (action) {
            is SettingsAction.OnBackClicked -> onBackClicked()
            is SettingsAction.SetVibrationEnabled ->
                onSetVibrationEnabled(action.vibrationEnabled)

            is SettingsAction.SetLocationUpdateSecondsInterval ->
                onSetLocationUpdateSecondsInterval(action.locationUpdateSecondsInterval)
        }
    }

    private fun onBackClicked() {
        navController.popBackStack()
    }

    private fun onSetVibrationEnabled(vibrationEnabled: Boolean) {
        _state.value = _state.value.copy(vibrationEnabled = vibrationEnabled)
    }

    private fun onSetLocationUpdateSecondsInterval(locationUpdateSecondsInterval: Int) {
        _state.update { state ->
            state.copy(
                locationUpdateSecondsInterval = locationUpdateSecondsInterval
            )
        }
    }
}