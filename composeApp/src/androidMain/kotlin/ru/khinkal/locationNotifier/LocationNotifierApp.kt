package ru.khinkal.locationNotifier

import android.app.Application
import kmp.core.AndroidSystemDeps
import ru.khinkal.locationNotifier.di.AppComponent
import ru.khinkal.locationNotifier.di.deps.DepsProvidingHelper

class LocationNotifierApp : Application() {

    private val appComponent by lazy {
        AppComponent(
            systemDeps = AndroidSystemDeps(this),
        )
    }

    // Add class for initialize?
    override fun onCreate() {
        INSTANCE = this
        provideDeps()
        super.onCreate()
    }

    private fun provideDeps() {
        DepsProvidingHelper.provideDepsToFeatures(appComponent.depsProvider)
    }

    companion object {

        lateinit var INSTANCE: LocationNotifierApp
            private set
    }
}
