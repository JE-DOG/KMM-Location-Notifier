package ru.khinkal.locationNotifier.feature.main.presentation.vm.model

import ru.khinkal.locationNotifier.core.errors.UiError
import ru.khinkal.locationNotifier.feature.main.domain.model.GeoPoint

data class MainState(
    val geoPoints: List<GeoPoint>,
    val isLoading: Boolean,
    val error: UiError?,
) {

    companion object {

        val EMPTY
            get() = MainState(
                geoPoints = emptyList(),
                isLoading = false,
                error = null,
            )
    }
}
