package ru.khinkal.locationNotifier.feature.goalBroadcaster

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.takeWhile
import ru.khinkal.locationNotifier.core.ext.location.distanceInMetersTo
import ru.khinkal.locationNotifier.core.location.LocationService
import ru.khinkal.locationNotifier.core.notification.NotificationService
import ru.khinkal.locationNotifier.core.notification.model.NotificationData
import ru.khinkal.locationNotifier.core.notification.model.NotificationNotifyType
import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint

class IosGoalGeoPointBroadcaster(
    private val notificationService: NotificationService,
    private val locationService: LocationService,
    private val coroutineScope: CoroutineScope,
) : GoalGeoPointBroadcaster {

    override fun startBroadcast(goalGeoPoint: GoalGeoPoint) {
        val notificationData = goalGeoPoint.createDefaultNotificationData()
        locationService.geoPoint
            .filterNotNull()
            .takeWhile { geoPoint ->
                val metersToGoal = goalGeoPoint.geoPoint distanceInMetersTo geoPoint
                if (metersToGoal > goalGeoPoint.meters) {
                    val updatedNotificationData = notificationData
                        .copyAndUpdateDescription(metersToGoal)
                    notificationService.notify(updatedNotificationData)
                    true
                } else {
                    val updatedNotificationData = notificationData
                        .copyAndUpdateForReachGoal()
                    notificationService.notify(updatedNotificationData)
                    false
                }
            }
            .launchIn(coroutineScope)
    }

    private fun GoalGeoPoint.createDefaultNotificationData(): NotificationData {
        return NotificationData(
            id = id,
            title = name,
            notifyType = NotificationNotifyType.Silent,
        )
    }

    private fun NotificationData.copyAndUpdateDescription(
        metersToGoal: Int,
    ): NotificationData {
        return copy(
            description = getDescription(metersToGoal),
        )
    }

    private fun getDescription(metersToGoal: Int): String {
        return "$metersToGoal meters"
    }

    private fun NotificationData.copyAndUpdateForReachGoal(): NotificationData {
        return copy(
            notifyType = NotificationNotifyType.Sound,
        )
    }
}
