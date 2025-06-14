package ru.khinkal.locationNotifier.feature.goalBroadcaster.di

import kmp.core.deps.SystemDeps
import kmp.feature.goalBroadcaster.GoalGeoPointBroadcasterFactory
import kotlinx.coroutines.CoroutineScope
import ru.khinkal.locationNotifier.feature.goalBroadcaster.GoalGeoPointBroadcaster

class GoalGeoPointBroadcasterModule {

    fun provideGoalGeoPointBroadcaster(
        systemDeps: SystemDeps,
        coroutineScope: CoroutineScope,
    ): GoalGeoPointBroadcaster {
        return GoalGeoPointBroadcasterFactory.create(
            systemDeps = systemDeps,
            coroutineScope = coroutineScope,
        )
    }
}
