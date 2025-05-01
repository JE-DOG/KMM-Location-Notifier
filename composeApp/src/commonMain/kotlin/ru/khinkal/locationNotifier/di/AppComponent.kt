package ru.khinkal.locationNotifier.di

import kmp.core.deps.EmptySystemDeps
import kmp.core.deps.SystemDeps
import ru.khinkal.locationNotifier.core.di.CoreModule
import ru.khinkal.locationNotifier.di.deps.DepsProvider

class AppComponent(
    systemDeps: SystemDeps = EmptySystemDeps(),
) {

    private val coreModule = CoreModule()

    val pathManager by lazy {
        coreModule.providePathManager(systemDeps)
    }

    val depsProvider: DepsProvider by lazy {
        DepsProvider(
            pathManager = pathManager,
            systemDeps = systemDeps,
        )
    }
}
