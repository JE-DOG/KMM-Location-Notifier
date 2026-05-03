package ru.khinkal.locationNotifier.feature.goalBroadcaster

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.flow.update
import ru.khinkal.locationNotifier.core.ext.location.distanceInMetersTo
import ru.khinkal.locationNotifier.core.location.LocationService
import ru.khinkal.locationNotifier.core.notification.NotificationService
import ru.khinkal.locationNotifier.core.notification.model.NotificationData
import ru.khinkal.locationNotifier.core.notification.model.NotificationNotifyType
import ru.khinkal.locationNotifier.feature.goalBroadcaster.model.GoalBroadcastProgress
import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint

class IosGoalGeoPointBroadcaster(
    private val notificationService: NotificationService,
    private val locationService: LocationService,
    private val coroutineScope: CoroutineScope,
) : GoalGeoPointBroadcaster {

    private val _activeGoalProgress = MutableStateFlow<GoalBroadcastProgress?>(null)
    override val activeGoalProgress: StateFlow<GoalBroadcastProgress?> = _activeGoalProgress
        .asStateFlow()

    private var currentBroadcastJob: Job? = null

    override fun startBroadcast(goalGeoPoint: GoalGeoPoint) {
        val notificationData = goalGeoPoint.createDefaultNotificationData()
        var totalDistance = 0

        currentBroadcastJob?.cancel()
        locationService.geoPoint
            .filterNotNull()
            .takeWhile { geoPoint ->
                val metersToGoal = goalGeoPoint.geoPoint distanceInMetersTo geoPoint
                if (metersToGoal > totalDistance) {
                    totalDistance = metersToGoal
                }

                if (metersToGoal > goalGeoPoint.meters) {
                    val progress = if (totalDistance > 0) {
                        ((totalDistance - metersToGoal).toFloat() / totalDistance).coerceIn(0f, 1f)
                    } else {
                        1f
                    }
                    _activeGoalProgress.update {
                        GoalBroadcastProgress(
                            goalName = goalGeoPoint.name,
                            metersToGoal = metersToGoal,
                            progress = progress,
                        )
                    }

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
            .onCompletion {
                _activeGoalProgress.update { null }
            }
            .launchIn(coroutineScope)
            .also { currentBroadcastJob = it }
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
