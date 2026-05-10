package ru.khinkal.locationNotifier.feature.main.presentation.vm.model

import ru.khinkal.locationNotifier.feature.goalBroadcaster.model.GoalBroadcastProgress

sealed interface LocationListeningStatus {

    data object TryingToGetLocationData : LocationListeningStatus

    data class Tracking(
        val progress: GoalBroadcastProgress,
    ) : LocationListeningStatus
}
