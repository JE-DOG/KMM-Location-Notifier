package ru.khinkal.locationNotifier

import android.app.Application
import kmp.core.AndroidSystemDeps
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import ru.khinkal.locationNotifier.di.metro.FeatureDepsFactoryProvider
import ru.khinkal.locationNotifier.di.metro.createAppGraph

class LocationNotifierApp : Application() {

    override fun onCreate() {
        INSTANCE = this
        val appGraph = createAppGraph(
            systemDeps = AndroidSystemDeps(this),
            coroutineScope = CoroutineScope(
                Dispatchers.IO + SupervisorJob(),
            ),
        )
        FeatureDepsFactoryProvider.provide(
            appGraph = appGraph,
        )
        super.onCreate()
    }

    companion object {

        lateinit var INSTANCE: LocationNotifierApp
            private set
    }
}
