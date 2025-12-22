package ru.khinkal.locationNotifier.feature.main.presentation.di.deps

import kotlin.properties.Delegates.notNull

fun interface MainDepsFactory {

    fun create(): MainDeps

    companion object {

        var INSTANCE: MainDepsFactory by notNull()
            private set

        fun provideInstance(instance: MainDepsFactory) {
            INSTANCE = instance
        }
    }
}
