package ru.khinkal.locationNotifier.di.metro

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.createGraphFactory
import kmp.core.deps.SystemDeps
import kotlinx.coroutines.CoroutineScope
import ru.khinkal.locationNotifier.di.metro.AppGraph.Factory
import ru.khinkal.locationNotifier.di.metro.bindings.AppCoreBindings
import ru.khinkal.locationNotifier.di.metro.bindings.AppFeatureDepsBindings
import ru.khinkal.locationNotifier.feature.createGoal.presentation.di.deps.CreateGoalDepsFactory
import ru.khinkal.locationNotifier.feature.main.presentation.di.deps.MainDepsFactory

@DependencyGraph(
    scope = AppScope::class,
    bindingContainers = [
        AppCoreBindings::class,
        AppFeatureDepsBindings::class,
    ],
)
interface AppGraph {

    val mainDepsFactory: MainDepsFactory
    val createGoalDepsFactory: CreateGoalDepsFactory

    @DependencyGraph.Factory
    fun interface Factory {

        fun create(
            @Provides systemDeps: SystemDeps,
            @Provides coroutineScope: CoroutineScope,
        ): AppGraph
    }
}

fun createAppGraph(
    systemDeps: SystemDeps,
    coroutineScope: CoroutineScope,
): AppGraph {
    return createGraphFactory<Factory>()
        .create(
            systemDeps = systemDeps,
            coroutineScope = coroutineScope,
        )
}
