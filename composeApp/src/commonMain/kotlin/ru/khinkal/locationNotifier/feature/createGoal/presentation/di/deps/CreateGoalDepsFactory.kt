package ru.khinkal.locationNotifier.feature.createGoal.presentation.di.deps

import kotlin.properties.Delegates.notNull

fun interface CreateGoalDepsFactory {

    fun create(): CreateGoalDeps

    companion object Companion {

        var INSTANCE: CreateGoalDepsFactory by notNull()
            private set

        fun provideInstance(instance: CreateGoalDepsFactory) {
            INSTANCE = instance
        }
    }
}
