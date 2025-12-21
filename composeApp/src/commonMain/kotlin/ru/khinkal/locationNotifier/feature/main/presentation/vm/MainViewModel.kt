package ru.khinkal.locationNotifier.feature.main.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import ru.khinkal.locationNotifier.core.ext.coroutines.launchCatching
import ru.khinkal.locationNotifier.feature.createGoal.presentation.navigation.CreateGoalScreen
import ru.khinkal.locationNotifier.feature.goalBroadcaster.GoalGeoPointBroadcaster
import ru.khinkal.locationNotifier.feature.main.domain.GoalGeoPointRepository
import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint
import ru.khinkal.locationNotifier.feature.main.presentation.vm.model.MainAction
import ru.khinkal.locationNotifier.feature.main.presentation.vm.model.MainState
import ru.khinkal.locationNotifier.feature.settings.presentation.navigation.SettingsScreen

class MainViewModel(
    private val navController: NavController,
    private val goalGeoPointRepository: GoalGeoPointRepository,
    private val goalGeoPointBroadcaster: GoalGeoPointBroadcaster,
) : ViewModel() {

    private val _state: MutableStateFlow<MainState> = MutableStateFlow(MainState.EMPTY)
    val state: StateFlow<MainState> = _state
        .onStart { observeLocations() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = _state.value,
        )

    private fun observeLocations() {
        _state.update { it.copy(isLoading = true) }
        goalGeoPointRepository.getAll()
            .onEach { locations ->
                _state.update { state ->
                    state.copy(
                        goalGeoPoints = locations,
                        isLoading = false,
                        error = null,
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    fun action(action: MainAction) {
        when (action) {
            is MainAction.OnAddLocationClick -> onAddLocationClick()
            is MainAction.OnSettingsClick -> onSettingsClick()
            is MainAction.OnLocationClick -> onLocationClick(action.goalGeoPoint)
            is MainAction.OnDeleteLocationClick ->
                onDeleteLocationClick(action.goalGeoPoint)

            is MainAction.OnEditLocationClick ->
                onEditLocationClick(action.goalGeoPoint)
        }
    }

    private fun onAddLocationClick() {
        navController.navigate(CreateGoalScreen())
    }

    private fun onSettingsClick() {
        navController.navigate(SettingsScreen)
    }

    private fun onLocationClick(goalGeoPoint: GoalGeoPoint) {
        goalGeoPointBroadcaster.startBroadcast(goalGeoPoint)
    }

    private fun onDeleteLocationClick(goalGeoPoint: GoalGeoPoint) {
        viewModelScope.launchCatching {
            goalGeoPointRepository.delete(goalGeoPoint)
        }
    }

    private fun onEditLocationClick(goalGeoPoint: GoalGeoPoint) {
        navController.navigate(CreateGoalScreen(goalGeoPoint))
    }
}
