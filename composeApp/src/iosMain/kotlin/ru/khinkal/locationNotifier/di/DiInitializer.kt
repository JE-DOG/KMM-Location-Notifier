package ru.khinkal.locationNotifier.di

import kmp.core.deps.EmptySystemDeps
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import ru.khinkal.locationNotifier.di.metro.FeatureDepsFactoryProvider
import ru.khinkal.locationNotifier.di.metro.createAppGraph

// NOT DELETE
// Use from iosApp
class DiInitializer {

    fun invoke() {
        val appGraph = createAppGraph(
            systemDeps = EmptySystemDeps(),
            coroutineScope = CoroutineScope(
                Dispatchers.IO + SupervisorJob(),
            ),
        )

        FeatureDepsFactoryProvider.provide(
            appGraph = appGraph,
        )
    }
}
