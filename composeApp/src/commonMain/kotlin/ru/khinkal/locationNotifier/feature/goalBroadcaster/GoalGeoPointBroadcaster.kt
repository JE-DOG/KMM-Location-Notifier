package ru.khinkal.locationNotifier.feature.goalBroadcaster

import kotlinx.coroutines.flow.StateFlow
import ru.khinkal.locationNotifier.feature.goalBroadcaster.model.GoalBroadcastProgress
import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint

interface GoalGeoPointBroadcaster {

    val activeGoalProgress: StateFlow<GoalBroadcastProgress?>

    fun startBroadcast(goalGeoPoint: GoalGeoPoint)
}
