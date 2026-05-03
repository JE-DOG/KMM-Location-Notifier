package ru.khinkal.locationNotifier.feature.main.presentation.vm.model

import ru.khinkal.locationNotifier.core.errors.UiError
import ru.khinkal.locationNotifier.feature.goalBroadcaster.model.GoalBroadcastProgress
import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint

data class MainState(
    val goalGeoPoints: List<GoalGeoPoint>,
    val activeGoalProgress: GoalBroadcastProgress?,
    val isLoading: Boolean,
    val error: UiError?,
) {

    companion object {

        val EMPTY
            get() = MainState(
                goalGeoPoints = emptyList(),
                activeGoalProgress = null,
                isLoading = false,
                error = null,
            )
    }
}
