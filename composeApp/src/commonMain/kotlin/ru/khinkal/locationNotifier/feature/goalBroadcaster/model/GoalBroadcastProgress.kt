package ru.khinkal.locationNotifier.feature.goalBroadcaster.model

data class GoalBroadcastProgress(
    val goalName: String,
    val metersToGoal: Int,
    val progress: Float,
)
