package ru.khinkal.locationNotifier

import android.app.Application
import io.openmobilemaps.mapscore.MapsCore
import kmp.core.AndroidSystemDeps
import ru.khinkal.locationNotifier.di.AppComponent
import ru.khinkal.locationNotifier.di.deps.DepsProvidingHelper

class LocationNotifierApp : Application() {

    private val appComponent by lazy {
        AppComponent(AndroidSystemDeps(this))
    }

    // Add class for initialize?
    override fun onCreate() {
        INSTANCE = this
        DepsProvidingHelper.provideDepsToFeatures(appComponent.depsProvider)
        super.onCreate()
        MapsCore.initialize()
    }

    companion object {

        lateinit var INSTANCE: LocationNotifierApp
            private set
    }
}
