package ru.khinkal.locationNotifier.core.di

import kmp.core.deps.EmptySystemDeps
import kmp.core.deps.SystemDeps
import kmp.core.path.PathManager
import kmp.core.path.createPathHelper

class CoreModule {

    fun providePathManager(
        systemDeps: SystemDeps = EmptySystemDeps(),
    ): PathManager {
        return createPathHelper(systemDeps)
    }
}
