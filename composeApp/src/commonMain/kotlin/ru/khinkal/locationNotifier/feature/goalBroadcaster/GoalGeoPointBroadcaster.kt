package ru.khinkal.locationNotifier.feature.goalBroadcaster

import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint

interface GoalGeoPointBroadcaster {

    fun startBroadcast(goalGeoPoint: GoalGeoPoint)
}
