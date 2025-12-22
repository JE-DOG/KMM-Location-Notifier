package kmp.feature.goalBroadcaster

import kmp.core.deps.SystemDeps
import kotlinx.coroutines.CoroutineScope
import ru.khinkal.locationNotifier.feature.goalBroadcaster.GoalGeoPointBroadcaster

expect object GoalGeoPointBroadcasterFactory {

    fun create(
        systemDeps: SystemDeps,
        coroutineScope: CoroutineScope,
    ): GoalGeoPointBroadcaster
}
