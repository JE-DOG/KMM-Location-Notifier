package ru.khinkal.locationNotifier.di.metro

import ru.khinkal.locationNotifier.feature.createGoal.presentation.di.deps.CreateGoalDepsFactory
import ru.khinkal.locationNotifier.feature.main.presentation.di.deps.MainDepsFactory

object FeatureDepsFactoryProvider {

    fun provide(appGraph: AppGraph) {
        MainDepsFactory.provideInstance(appGraph.mainDepsFactory)
        CreateGoalDepsFactory.provideInstance(appGraph.createGoalDepsFactory)
    }
}
