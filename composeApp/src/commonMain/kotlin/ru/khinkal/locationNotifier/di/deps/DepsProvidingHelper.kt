package ru.khinkal.locationNotifier.di.deps

import ru.khinkal.locationNotifier.feature.createGoal.presentation.di.deps.CreateGoalDepsFactory
import ru.khinkal.locationNotifier.feature.main.presentation.di.deps.MainDepsFactory

object DepsProvidingHelper {

    fun provideDepsToFeatures(depsProvider: DepsProvider) {
        MainDepsFactory.provideInstance { depsProvider }
        CreateGoalDepsFactory.provideInstance { depsProvider }
    }
}
