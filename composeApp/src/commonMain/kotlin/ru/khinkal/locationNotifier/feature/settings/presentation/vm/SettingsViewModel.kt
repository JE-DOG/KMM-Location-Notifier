package ru.khinkal.locationNotifier.feature.settings.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavController
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import ru.khinkal.locationNotifier.core.ext.coroutines.canceledJob
import ru.khinkal.locationNotifier.core.ext.coroutines.launchCatching
import ru.khinkal.locationNotifier.feature.settings.data.factory.SettingsManagerImplFactory
import ru.khinkal.locationNotifier.feature.settings.domain.SettingsManager
import ru.khinkal.locationNotifier.feature.settings.presentation.vm.model.SettingsAction
import ru.khinkal.locationNotifier.feature.settings.presentation.vm.model.SettingsUiState

class SettingsViewModel(
    private val navController: NavController,
    private val settingsManager: SettingsManager,
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsUiState.EMPTY)
    val state: StateFlow<SettingsUiState> = _state
        .onStart { fetchData() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = _state.value,
        )

    private var setLocationUpdateSecondsJob by canceledJob()
    private var setIsVibrationEnabledJob by canceledJob()

    private fun fetchData() {
        viewModelScope.launchCatching(
            onFinally = {
                _state.update { state ->
                    state.copy(isLoading = false)
                }
            },
        ) {
            _state.update { state ->
                state.copy(isLoading = true)
            }
            val locationUpdateSeconds = async { settingsManager.getLocationUpdateSeconds() }
            val isVibrationEnabled = async { settingsManager.isVibrationEnabled() }

            _state.update { state ->
                state.copy(
                    locationUpdateSeconds = locationUpdateSeconds.await(),
                    isVibrationEnabled = isVibrationEnabled.await(),
                )
            }
        }
    }

    fun onAction(action: SettingsAction) {
        when (action) {
            is SettingsAction.OnBackClicked -> onBackClicked()
            is SettingsAction.SetVibrationEnabled ->
                onSetVibrationEnabled(action.vibrationEnabled)

            is SettingsAction.SetLocationUpdateSeconds ->
                onSetLocationUpdateSeconds(action.locationUpdateSecondsInterval)
        }
    }

    private fun onBackClicked() {
        navController.popBackStack()
    }

    private fun onSetVibrationEnabled(vibrationEnabled: Boolean) {
        _state.value = _state.value.copy(isVibrationEnabled = vibrationEnabled)

        setIsVibrationEnabledJob = viewModelScope.launchCatching {
            settingsManager.setIsVibrationEnabled(vibrationEnabled)
        }
    }

    private fun onSetLocationUpdateSeconds(locationUpdateSeconds: Int) {
        _state.update { state ->
            state.copy(
                locationUpdateSeconds = locationUpdateSeconds
            )
        }

        setLocationUpdateSecondsJob = viewModelScope.launchCatching {
            settingsManager.setLocationUpdateSeconds(locationUpdateSeconds)
        }
    }

    companion object {

        fun createFactory(
            navController: NavController,
            settingsManager: SettingsManager = SettingsManagerImplFactory.create(),
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                SettingsViewModel(
                    settingsManager = settingsManager,
                    navController = navController,
                )
            }
        }
    }
}
