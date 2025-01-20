package ru.khinkal.locationNotifier.feature.createGoal.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.khinkal.locationNotifier.core.location.model.BaseGeoPoint
import ru.khinkal.locationNotifier.core.location.utill.observeResult
import ru.khinkal.locationNotifier.core.location.utill.returnResult
import ru.khinkal.locationNotifier.feature.createGoal.presentation.vm.model.CreateGoalAction
import ru.khinkal.locationNotifier.feature.createGoal.presentation.vm.model.CreateGoalState
import ru.khinkal.locationNotifier.feature.locationList.domain.model.GeoPoint
import ru.khinkal.locationNotifier.feature.setGeoPoint.navigation.SetGeoPointScreen
import ru.khinkal.locationNotifier.shared.navigation.ResultKeys

class CreateGoalViewModel(
    private val navController: NavController,
): ViewModel() {

    private val _state = MutableStateFlow(CreateGoalState())
    val state = _state.asStateFlow()

    init {
        observeBaseGeoPoint()
    }

    private fun observeBaseGeoPoint() {
        viewModelScope.launch {
            val baseGeoPointSelectedJsonFlow = navController.observeResult<String?>(
                key = ResultKeys.GEO_POINT_SELECTED,
                initialValue = null,
            ) ?: return@launch
            val geoPointSelectedJson = baseGeoPointSelectedJsonFlow
                .filterNotNull()
                .first()
            val baseGeoPointSelected = Json.decodeFromString<BaseGeoPoint>(geoPointSelectedJson)

            _state.update { it.copy(baseGeoPoint = baseGeoPointSelected) }
        }
    }

    fun onAction(action: CreateGoalAction) {
        when (action) {
            is CreateGoalAction.SetProperty -> onSetPropertyAction(action)
            CreateGoalAction.GoBack -> onBack()
            CreateGoalAction.StartBroadcast -> onStartBroadcast()
            CreateGoalAction.OnSetBaseGeoPointClicked -> onSetBaseGeoPointClicked()
        }
    }

    private fun onBack() {
        navController.popBackStack()
    }

    private fun onStartBroadcast() {
        val geoPoint = createGeoPoint() ?: return
        val geoPointJson = Json.encodeToString(geoPoint)
        navController.returnResult(
            key = ResultKeys.GOAL_CREATED,
            result = geoPointJson,
        )
        navController.popBackStack()
    }

    private fun onSetBaseGeoPointClicked() {
        navController.navigate(SetGeoPointScreen)
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

    private fun createGeoPoint(): GeoPoint? {
        val state = state.value
        if (!isInputDataValid(state)) return null
        return state.run {
            GeoPoint(
                name = name,
                meters = meters!!,
                latitude = baseGeoPoint!!.latitude,
                longitude = baseGeoPoint.latitude,
            )
        }
    }

    private fun isInputDataValid(state: CreateGoalState): Boolean {
        with(state) {
            if (name.isEmpty()) return false
            if (meters == null || meters < 0) return false
            if (baseGeoPoint == null) return false
        }
        return true
    }
}