package ru.khinkal.locationNotifier.feature.settings.presentation.di.deps

import kotlin.properties.Delegates.notNull

fun interface SettingsDepsFactory {

    fun create(): SettingsDeps

    companion object {

        var INSTANCE: SettingsDepsFactory by notNull()
            private set

        fun provideInstance(instance: SettingsDepsFactory) {
            INSTANCE = instance
        }
    }
}
