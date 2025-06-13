package ru.khinkal.locationNotifier.di

import kmp.core.deps.EmptySystemDeps
import kmp.core.deps.SystemDeps
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import ru.khinkal.locationNotifier.core.di.CoreModule
import ru.khinkal.locationNotifier.di.deps.DepsProvider

class AppComponent(
    systemDeps: SystemDeps = EmptySystemDeps(),
    coroutineScope: CoroutineScope = CoroutineScope(
        Dispatchers.IO + SupervisorJob()
    ),
) {

    private val coreModule by lazy { CoreModule() }

    val pathManager by lazy {
        coreModule.providePathManager(systemDeps)
    }

    val depsProvider: DepsProvider by lazy {
        DepsProvider(
            pathManager = pathManager,
            systemDeps = systemDeps,
            coroutineScope = coroutineScope,
        )
    }
}
