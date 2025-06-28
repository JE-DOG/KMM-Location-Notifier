package ru.khinkal.locationNotifier.feature.settings.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import ru.khinkal.locationNotifier.core.ext.coroutines.canceledJob
import ru.khinkal.locationNotifier.core.ext.coroutines.launchCatching
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

    private var setNotifyEnabledJob by canceledJob()

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

            _state.update { state ->
                state.copy(
                    isNotifyEnabled = settingsManager.isNotifyEnabled(),
                )
            }
        }
    }

    fun onAction(action: SettingsAction) {
        when (action) {
            is SettingsAction.OnBackClicked -> onBackClicked()
            is SettingsAction.SetNotifyEnabled ->
                onSetNotifyEnabled(action.notifyEnabled)
        }
    }

    private fun onBackClicked() {
        navController.popBackStack()
    }

    private fun onSetNotifyEnabled(vibrationEnabled: Boolean) {
        _state.value = _state.value.copy(isNotifyEnabled = vibrationEnabled)

        setNotifyEnabledJob = viewModelScope.launchCatching {
            settingsManager.setIsNotifyEnabled(vibrationEnabled)
        }
    }
}
