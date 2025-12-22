package ru.khinkal.locationNotifier.di

import ru.khinkal.locationNotifier.di.deps.DepsProvidingHelper

// NOT DELETE
// Use from iosApp
class DiInitializer {

    fun invoke() {
        val appComponent = AppComponent()
        DepsProvidingHelper.provideDepsToFeatures(appComponent.depsProvider)
    }
}
