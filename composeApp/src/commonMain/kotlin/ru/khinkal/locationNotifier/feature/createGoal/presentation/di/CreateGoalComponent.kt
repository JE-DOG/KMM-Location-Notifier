package ru.khinkal.locationNotifier.feature.createGoal.presentation.di

import ru.khinkal.locationNotifier.feature.createGoal.presentation.di.deps.CreateGoalDeps
import ru.khinkal.locationNotifier.feature.createGoal.presentation.di.deps.CreateGoalDepsFactory
import ru.khinkal.locationNotifier.feature.main.data.di.GoalGeoPointDataModule
import ru.khinkal.locationNotifier.feature.main.domain.GoalGeoPointRepository

class CreateGoalComponent(
    private val deps: CreateGoalDeps = CreateGoalDepsFactory.INSTANCE.create(),
) {

    private val goalGeoPointDataModule: GoalGeoPointDataModule by lazy {
        GoalGeoPointDataModule()
    }

    val goalGeoPointRepository: GoalGeoPointRepository by lazy {
        goalGeoPointDataModule.provideGoalGeoPointRepository(
            appDataBase = deps.appDataBase,
        )
    }

    companion object {

        val INSTANCE by lazy { CreateGoalComponent() }
    }
}
