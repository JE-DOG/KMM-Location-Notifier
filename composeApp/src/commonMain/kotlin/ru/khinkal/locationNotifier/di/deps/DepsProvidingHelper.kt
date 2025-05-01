package ru.khinkal.locationNotifier.di.deps

import ru.khinkal.locationNotifier.feature.main.presentation.di.deps.MainDepsFactory
import ru.khinkal.locationNotifier.feature.settings.presentation.di.deps.SettingsDepsFactory

object DepsProvidingHelper {

    fun provideDepsToFeatures(depsProvider: DepsProvider) {
        MainDepsFactory.provideInstance { depsProvider }
        SettingsDepsFactory.provideInstance { depsProvider }
    }
}
