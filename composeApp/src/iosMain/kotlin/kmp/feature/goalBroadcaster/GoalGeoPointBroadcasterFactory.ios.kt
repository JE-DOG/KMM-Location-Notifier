package kmp.feature.goalBroadcaster

import kmp.core.deps.SystemDeps
import kotlinx.coroutines.CoroutineScope
import ru.khinkal.locationNotifier.feature.goalBroadcaster.GoalGeoPointBroadcaster
import ru.khinkal.locationNotifier.feature.goalBroadcaster.IosGoalGeoPointBroadcaster
import ru.khinkal.locationNotifier.feature.goalBroadcaster.di.createGoalGeoPointBroadcasterGraph
import ru.khinkal.locationNotifier.feature.goalBroadcaster.di.deps.GoalGeoPointBroadcasterDeps

actual object GoalGeoPointBroadcasterFactory {

    actual fun create(
        systemDeps: SystemDeps,
        coroutineScope: CoroutineScope,
    ): GoalGeoPointBroadcaster {
        val deps = object : GoalGeoPointBroadcasterDeps {
            override val systemDeps: SystemDeps = systemDeps
            override val coroutineScope: CoroutineScope = coroutineScope
        }
        val graph = createGoalGeoPointBroadcasterGraph(deps = deps)

        return IosGoalGeoPointBroadcaster(
            notificationService = graph.notificationService,
            locationService = graph.locationService,
            coroutineScope = coroutineScope,
        )
    }
}
