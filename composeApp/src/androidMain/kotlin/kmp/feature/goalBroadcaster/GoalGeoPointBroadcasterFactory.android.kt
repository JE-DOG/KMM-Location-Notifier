package kmp.feature.goalBroadcaster

import kmp.core.asAndroid
import kmp.core.deps.SystemDeps
import kotlinx.coroutines.CoroutineScope
import ru.khinkal.locationNotifier.feature.goalBroadcaster.GoalGeoPointBroadcaster
import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint
import ru.khinkal.locationNotifier.feature.main.presentation.broadcast.GoalGeoPointBroadcastForegroundService

actual object GoalGeoPointBroadcasterFactory {

    actual fun create(
        systemDeps: SystemDeps,
        coroutineScope: CoroutineScope,
    ): GoalGeoPointBroadcaster {
        return object : GoalGeoPointBroadcaster {

            override fun startBroadcast(goalGeoPoint: GoalGeoPoint) {
                GoalGeoPointBroadcastForegroundService.start(
                    context = systemDeps.asAndroid().applicationContext,
                    goalGeoPoint = goalGeoPoint,
                )
            }
        }
    }
}
