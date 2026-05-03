package ru.khinkal.locationNotifier.feature.main.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import ru.khinkal.locationNotifier.core.ext.coroutines.launchCatching
import ru.khinkal.locationNotifier.feature.createGoal.presentation.navigation.CreateGoalRoute
import ru.khinkal.locationNotifier.feature.goalBroadcaster.GoalGeoPointBroadcaster
import ru.khinkal.locationNotifier.feature.main.domain.GoalGeoPointRepository
import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint
import ru.khinkal.locationNotifier.feature.main.presentation.vm.model.LocationListeningStatus
import ru.khinkal.locationNotifier.feature.main.presentation.vm.model.MainAction
import ru.khinkal.locationNotifier.feature.main.presentation.vm.model.MainEvent
import ru.khinkal.locationNotifier.feature.main.presentation.vm.model.MainState

class MainViewModel(
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

    init {
        observeGoalProgress()
    }

    private val _event = Channel<MainEvent>(capacity = Channel.BUFFERED)
    val event = _event.receiveAsFlow()

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

    private fun observeGoalProgress() {
        goalGeoPointBroadcaster.activeGoalProgress
            .onEach { progress ->
                _state.update { state ->
                    state.copy(
                        locationListeningStatus = when (progress) {
                            null -> null
                            else -> LocationListeningStatus.Tracking(progress)
                        },
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    fun action(action: MainAction) {
        when (action) {
            is MainAction.OnAddLocationClick -> onAddLocationClick()
            is MainAction.OnLocationClick -> onLocationClick(action.goalGeoPoint)
            is MainAction.OnDeleteLocationClick ->
                onDeleteLocationClick(action.goalGeoPoint)

            is MainAction.OnEditLocationClick ->
                onEditLocationClick(action.goalGeoPoint)
        }
    }

    private fun onAddLocationClick() {
        _event.trySend(MainEvent.NavigateTo(CreateGoalRoute()))
    }

    private fun onLocationClick(goalGeoPoint: GoalGeoPoint) {
        goalGeoPointBroadcaster.startBroadcast(goalGeoPoint)
        _state.update { state ->
            state.copy(
                locationListeningStatus = LocationListeningStatus.TryingToGetLocationData,
            )
        }
    }

    private fun onDeleteLocationClick(goalGeoPoint: GoalGeoPoint) {
        viewModelScope.launchCatching {
            goalGeoPointRepository.delete(goalGeoPoint)
        }
    }

    private fun onEditLocationClick(goalGeoPoint: GoalGeoPoint) {
        _event.trySend(MainEvent.NavigateTo(CreateGoalRoute(goalGeoPoint)))
    }
}
