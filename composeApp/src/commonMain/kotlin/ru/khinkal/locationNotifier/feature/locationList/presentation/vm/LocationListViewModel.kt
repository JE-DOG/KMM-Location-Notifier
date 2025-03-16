package ru.khinkal.locationNotifier.feature.locationList.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import ru.khinkal.locationNotifier.core.errors.UiError
import ru.khinkal.locationNotifier.core.ext.coroutines.launchCatching
import ru.khinkal.locationNotifier.core.ext.location.observeResult
import ru.khinkal.locationNotifier.feature.createGoal.presentation.navigation.CreateGoalScreen
import ru.khinkal.locationNotifier.feature.locationList.domain.LocationRepository
import ru.khinkal.locationNotifier.feature.locationList.domain.model.GeoPoint
import ru.khinkal.locationNotifier.feature.locationList.presentation.broadcast.startBroadcast
import ru.khinkal.locationNotifier.feature.locationList.presentation.vm.model.LocationListAction
import ru.khinkal.locationNotifier.feature.locationList.presentation.vm.model.LocationListState
import ru.khinkal.locationNotifier.feature.settings.presentation.navigation.SettingsScreen
import ru.khinkal.locationNotifier.shared.navigation.ResultKeys

class LocationListViewModel(
    private val navController: NavController,
    private val locationRepository: LocationRepository,
) : ViewModel() {

    private val _state: MutableStateFlow<LocationListState> =
        MutableStateFlow(LocationListState.EMPTY)
    val state: StateFlow<LocationListState> get() = _state

    init {
        fetchLocations()
        observeCreatedLocation()
    }

    private fun fetchLocations() = viewModelScope.launchCatching {
        _state.update { state ->
            state.copy(isLoading = true)
        }
        val locations = locationRepository.getAllLocation()
        _state.update { state ->
            state.copy(
                geoPoints = locations,
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
                    val geoPoint = Json.decodeFromString<GeoPoint>(geoPointJson)
                    onGeoPointCreated(geoPoint)
                }
        }
    }

    private fun onGeoPointCreated(geoPoint: GeoPoint) {
        viewModelScope.launchCatching(
            onFailure = {
                _state.update { state ->
                    state.copy(
                        geoPoints = state.geoPoints - geoPoint,
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
                    geoPoints = state.geoPoints + geoPoint,
                )
            }
            locationRepository.addLocation(geoPoint)
        }
    }

    fun action(action: LocationListAction) {
        when (action) {
            is LocationListAction.OnAddLocationClick -> onAddLocationClick()
            is LocationListAction.OnSettingsClick -> onSettingsClick()
            is LocationListAction.OnLocationClick -> onLocationClick(action.geoPoint)
        }
    }

    private fun onAddLocationClick() {
        navController.navigate(CreateGoalScreen)
    }

    private fun onSettingsClick() {
        navController.navigate(SettingsScreen)
    }

    private fun onLocationClick(geoPoint: GeoPoint) {
        startBroadcast(geoPoint)
    }
}
