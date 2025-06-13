package ru.khinkal.locationNotifier.feature.main.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import ru.khinkal.locationNotifier.core.errors.UiError
import ru.khinkal.locationNotifier.core.ext.coroutines.launchCatching
import ru.khinkal.locationNotifier.core.ext.location.observeResult
import ru.khinkal.locationNotifier.feature.createGoal.presentation.navigation.CreateGoalScreen
import ru.khinkal.locationNotifier.feature.goalBroadcaster.GoalGeoPointBroadcaster
import ru.khinkal.locationNotifier.feature.main.domain.GoalGeoPointRepository
import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint
import ru.khinkal.locationNotifier.feature.main.presentation.vm.model.MainAction
import ru.khinkal.locationNotifier.feature.main.presentation.vm.model.MainState
import ru.khinkal.locationNotifier.feature.settings.presentation.navigation.SettingsScreen
import ru.khinkal.locationNotifier.shared.navigation.ResultKeys

class MainViewModel(
    private val navController: NavController,
    private val goalGeoPointRepository: GoalGeoPointRepository,
    private val goalGeoPointBroadcaster: GoalGeoPointBroadcaster,
) : ViewModel() {

    private val _state: MutableStateFlow<MainState> = MutableStateFlow(MainState.EMPTY)
    val state: StateFlow<MainState> = _state
        .onStart { fetchLocations() }
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = _state.value,
        )

    init {
        observeCreatedLocation()
    }

    private fun fetchLocations() = viewModelScope.launchCatching {
        _state.update { state ->
            state.copy(isLoading = true)
        }
        val locations = goalGeoPointRepository.getAll()
        _state.update { state ->
            state.copy(
                goalGeoPoints = locations,
                isLoading = false,
                error = null,
            )
        }
    }

    private fun observeCreatedLocation() {
        viewModelScope.launch {
            navController
                .observeResult<String?>(ResultKeys.GOAL_CREATED, null)
                ?.collect { geoPointJson ->
                    if (geoPointJson == null) return@collect
                    val goalGeoPoint = Json.decodeFromString<GoalGeoPoint>(geoPointJson)
                    onGeoPointCreated(goalGeoPoint)
                }
        }
    }

    private fun onGeoPointCreated(goalGeoPoint: GoalGeoPoint) {
        viewModelScope.launchCatching(
            onFailure = {
                _state.update { state ->
                    state.copy(
                        goalGeoPoints = state.goalGeoPoints - goalGeoPoint,
                        error = UiError(
                            title = it.message.orEmpty(),
                            description = it::class.simpleName.toString(),
                        )
                    )
                }
            },
        ) {
            _state.update { state ->
                state.copy(
                    goalGeoPoints = state.goalGeoPoints + goalGeoPoint,
                )
            }
            goalGeoPointRepository.add(goalGeoPoint)
        }
    }

    fun action(action: MainAction) {
        when (action) {
            is MainAction.OnAddLocationClick -> onAddLocationClick()
            is MainAction.OnSettingsClick -> onSettingsClick()
            is MainAction.OnLocationClick -> onLocationClick(action.goalGeoPoint)
        }
    }

    private fun onAddLocationClick() {
        navController.navigate(CreateGoalScreen)
    }

    private fun onSettingsClick() {
        navController.navigate(SettingsScreen)
    }

    private fun onLocationClick(goalGeoPoint: GoalGeoPoint) {
        goalGeoPointBroadcaster.startBroadcast(goalGeoPoint)
    }
}
