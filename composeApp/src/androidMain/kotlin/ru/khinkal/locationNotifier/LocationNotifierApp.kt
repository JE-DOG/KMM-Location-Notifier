package ru.khinkal.locationNotifier

import android.app.Application

class LocationNotifierApp: Application() {

    override fun onCreate() {
        INSTANCE = this
        super.onCreate()
    }

    companion object {

        lateinit var INSTANCE: LocationNotifierApp
            private set
    }
}