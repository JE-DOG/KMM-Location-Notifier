package ru.khinkal.locationNotifier

import android.app.Application
import io.openmobilemaps.mapscore.MapsCore

class LocationNotifierApp: Application() {

    override fun onCreate() {
        INSTANCE = this
        super.onCreate()
        MapsCore.initialize()
    }

    companion object {

        lateinit var INSTANCE: LocationNotifierApp
            private set
    }
}