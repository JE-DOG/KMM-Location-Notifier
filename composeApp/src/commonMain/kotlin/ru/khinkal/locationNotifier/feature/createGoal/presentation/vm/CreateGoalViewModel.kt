package ru.khinkal.locationNotifier.feature.createGoal.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.khinkal.locationNotifier.core.location.utill.returnResult
import ru.khinkal.locationNotifier.feature.createGoal.presentation.vm.model.CreateGoalAction
import ru.khinkal.locationNotifier.feature.createGoal.presentation.vm.model.CreateGoalState
import ru.khinkal.locationNotifier.feature.locationList.domain.model.GeoPoint
import ru.khinkal.locationNotifier.shared.navigation.ResultKeys

class CreateGoalViewModel(
    private val navController: NavController,
): ViewModel() {

    private val _state = MutableStateFlow(CreateGoalState())
    val state = _state.asStateFlow()

    fun onAction(action: CreateGoalAction) {
        when (action) {
            is CreateGoalAction.SetProperty -> onSetPropertyAction(action)
            CreateGoalAction.GoBack -> onBack()
            CreateGoalAction.StartBroadcast -> onStartBroadcast()
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

    private fun onSetPropertyAction(action: CreateGoalAction.SetProperty) {
        when (action) {
            is CreateGoalAction.SetProperty.SetName -> onSetName(action.name)
            is CreateGoalAction.SetProperty.SetMeters -> onSetMeters(action.meters)
            is CreateGoalAction.SetProperty.SetLatitude -> onSetLatitude(action.latitude)
            is CreateGoalAction.SetProperty.SetLongitude -> onSetLongitude(action.longitude)
        }
    }

    private fun onSetName(name: String) {
        _state.update { it.copy(name = name) }
    }

    private fun onSetMeters(meters: Int?) {
        _state.update { it.copy(meters = meters) }
    }

    private fun onSetLatitude(latitude: Double?) {
        _state.update { it.copy(latitude = latitude) }
    }

    private fun onSetLongitude(longitude: Double?) {
        _state.update { it.copy(longitude = longitude) }
    }

    private fun createGeoPoint(): GeoPoint? {
        val state = state.value
        if (!isInputDataValid(state)) return null
        return state.run {
            GeoPoint(
                name = name,
                meters = meters!!,
                latitude = latitude!!,
                longitude = longitude!!,
            )
        }
    }

    private fun isInputDataValid(state: CreateGoalState): Boolean {
        with(state) {
            if (name.isEmpty()) return false
            if (meters == null || meters < 0) return false
            if (longitude == null) return false
            if (latitude == null) return false
        }
        return true
    }
}