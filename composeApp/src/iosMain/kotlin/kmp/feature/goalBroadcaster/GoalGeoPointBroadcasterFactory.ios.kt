package kmp.feature.goalBroadcaster

import kmp.core.deps.SystemDeps
import kotlinx.coroutines.CoroutineScope
import ru.khinkal.locationNotifier.feature.goalBroadcaster.GoalGeoPointBroadcaster
import ru.khinkal.locationNotifier.feature.goalBroadcaster.IosGoalGeoPointBroadcaster
import ru.khinkal.locationNotifier.feature.goalBroadcaster.di.GoalGeoPointBroadcasterComponent

actual object GoalGeoPointBroadcasterFactory {

    actual fun create(
        systemDeps: SystemDeps,
        coroutineScope: CoroutineScope,
    ): GoalGeoPointBroadcaster {
        val component = GoalGeoPointBroadcasterComponent(
            coroutineScope = coroutineScope,
            systemDeps = systemDeps,
        )

        return IosGoalGeoPointBroadcaster(
            notificationService = component.notificationService,
            locationService = component.locationService,
            coroutineScope = component.coroutineScope,
        )
    }
}
