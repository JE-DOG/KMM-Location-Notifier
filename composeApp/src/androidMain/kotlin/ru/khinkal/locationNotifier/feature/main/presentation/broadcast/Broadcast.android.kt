package ru.khinkal.locationNotifier.feature.main.presentation.broadcast

import ru.khinkal.locationNotifier.LocationNotifierApp
import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint

actual fun startBroadcast(goalGeoPoint: GoalGeoPoint) {
    BroadcastLocationService.startBroadcast(
        context = LocationNotifierApp.INSTANCE,
        goalGeoPoint = goalGeoPoint,
    )
}
