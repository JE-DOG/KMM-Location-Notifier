package ru.khinkal.locationNotifier.feature.locationList.presentation.vm.model

import ru.khinkal.locationNotifier.core.errors.Error
import ru.khinkal.locationNotifier.feature.locationList.domain.model.GeoPoint

data class LocationListState(
    val geoPoints: List<GeoPoint>,
    val isLoading: Boolean,
    val error: Error?,
) {

    companion object {

        val EMPTY
            get() = LocationListState(
                geoPoints = emptyList(),
                isLoading = false,
                error = null,
            )
    }
}
