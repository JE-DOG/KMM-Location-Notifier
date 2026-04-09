package ru.khinkal.locationNotifier.feature.main.presentation.di.bindings

import dev.zacsweers.metro.BindingContainer
import dev.zacsweers.metro.Provides
import kmp.feature.goalBroadcaster.GoalGeoPointBroadcasterFactory
import ru.khinkal.locationNotifier.feature.goalBroadcaster.GoalGeoPointBroadcaster
import ru.khinkal.locationNotifier.feature.main.presentation.di.deps.MainDeps

@BindingContainer
class MainBroadcasterBindings {

    @Provides
    fun provideGoalGeoPointBroadcaster(
        deps: MainDeps,
    ): GoalGeoPointBroadcaster {
        return GoalGeoPointBroadcasterFactory.create(
            systemDeps = deps.systemDeps,
            coroutineScope = deps.coroutineScope,
        )
    }
}
