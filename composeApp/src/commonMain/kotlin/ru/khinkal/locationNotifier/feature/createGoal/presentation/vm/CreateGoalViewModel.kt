package ru.khinkal.locationNotifier.feature.createGoal.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.json.Json
import ru.khinkal.locationNotifier.core.ext.coroutines.launchCatching
import ru.khinkal.locationNotifier.core.location.model.GeoPoint
import ru.khinkal.locationNotifier.feature.createGoal.presentation.vm.model.CreateGoalAction
import ru.khinkal.locationNotifier.feature.createGoal.presentation.vm.model.CreateGoalEvent
import ru.khinkal.locationNotifier.feature.createGoal.presentation.vm.model.CreateGoalState
import ru.khinkal.locationNotifier.feature.main.domain.GoalGeoPointRepository
import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint
import ru.khinkal.locationNotifier.feature.setGeoPoint.navigation.SetGeoPointRoute

class CreateGoalViewModel(
    private val initialGoalGeoPoint: GoalGeoPoint?,
    private val geoPointRepository: GoalGeoPointRepository,
) : ViewModel() {

    private val isEdit = initialGoalGeoPoint != null

    private val _state = MutableStateFlow(
        CreateGoalState.initial(initialGoalGeoPoint)
    )
    val state = _state.asStateFlow()

    private val _event = Channel<CreateGoalEvent>(capacity = Channel.BUFFERED)
    val event = _event.receiveAsFlow()

    init {
        observeState()
    }

    private fun observeState() {
        state
            .onEach {
                _state.update { state ->
                    state.copy(
                        canCreateGoal = createGoalGeoPoint(state) != null,
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: CreateGoalAction) {
        when (action) {
            is CreateGoalAction.SetProperty -> onSetPropertyAction(action)
            CreateGoalAction.GoBack -> onBack()
            CreateGoalAction.CreateGoalGeoPoint -> onCreateGoalGeoPoint()
            CreateGoalAction.OnSetBaseGeoPointClicked -> onSetBaseGeoPointClicked()
        }
    }

    fun onGeoPointSelectedJson(geoPointSelectedJson: String) {
        val geoPointSelected = Json.decodeFromString<GeoPoint>(geoPointSelectedJson)
        _state.update { it.copy(geoPoint = geoPointSelected) }
    }

    private fun onBack() {
        _event.trySend(CreateGoalEvent.NavigateBack)
    }

    private fun onCreateGoalGeoPoint() {
        viewModelScope.launchCatching(
            onFailure = { throwable ->
                throwable.printStackTrace()
            },
        ) {
            val goalGeoPoint = createGoalGeoPoint() ?: return@launchCatching

            if (isEdit) {
                geoPointRepository.update(goalGeoPoint)
            } else {
                geoPointRepository.add(goalGeoPoint)
            }
            _event.trySend(CreateGoalEvent.NavigateBack)
        }
    }

    private fun onSetBaseGeoPointClicked() {
        _event.trySend(CreateGoalEvent.NavigateTo(SetGeoPointRoute))
    }

    private fun onSetPropertyAction(action: CreateGoalAction.SetProperty) {
        when (action) {
            is CreateGoalAction.SetProperty.SetName -> onSetName(action.name)
            is CreateGoalAction.SetProperty.SetMeters -> onSetMeters(action.meters)
        }
    }

    private fun onSetName(name: String) {
        _state.update { it.copy(name = name) }
    }

    private fun onSetMeters(meters: Int?) {
        _state.update { it.copy(meters = meters) }
    }

    private fun createGoalGeoPoint(
        state: CreateGoalState = this.state.value
    ): GoalGeoPoint? {
        if (state.name.isEmpty()) return null
        return GoalGeoPoint(
            id = initialGoalGeoPoint?.id ?: 0,
            name = state.name,
            meters = state.meters ?: return null,
            geoPoint = state.geoPoint ?: return null,
        )
    }
}
