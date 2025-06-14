package ru.khinkal.locationNotifier.feature.main.presentation.di

import ru.khinkal.locationNotifier.feature.goalBroadcaster.GoalGeoPointBroadcaster
import ru.khinkal.locationNotifier.feature.goalBroadcaster.di.GoalGeoPointBroadcasterModule
import ru.khinkal.locationNotifier.feature.main.data.di.GoalGeoPointDataModule
import ru.khinkal.locationNotifier.feature.main.domain.GoalGeoPointRepository
import ru.khinkal.locationNotifier.feature.main.presentation.di.deps.MainDeps
import ru.khinkal.locationNotifier.feature.main.presentation.di.deps.MainDepsFactory

class MainComponent(
    private val deps: MainDeps = MainDepsFactory.INSTANCE.create(),
) {

    private val goalGeoPointDataModule: GoalGeoPointDataModule by lazy {
        GoalGeoPointDataModule(
            systemDeps = deps.systemDeps,
            pathManager = deps.pathManager,
        )
    }
    private val goalGeoPointBroadcasterModule: GoalGeoPointBroadcasterModule by lazy {
        GoalGeoPointBroadcasterModule()
    }

    val goalGeoPointRepository: GoalGeoPointRepository by lazy {
        goalGeoPointDataModule.provideGoalGeoPointRepository()
    }
    val goalGeoPointBroadcaster: GoalGeoPointBroadcaster by lazy {
        goalGeoPointBroadcasterModule.provideGoalGeoPointBroadcaster(
            systemDeps = deps.systemDeps,
            coroutineScope = deps.coroutineScope,
        )
    }
}
