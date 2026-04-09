package ru.khinkal.locationNotifier.feature.main.presentation.di

import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.createGraphFactory
import ru.khinkal.locationNotifier.feature.goalBroadcaster.GoalGeoPointBroadcaster
import ru.khinkal.locationNotifier.feature.main.domain.GoalGeoPointRepository
import ru.khinkal.locationNotifier.feature.main.presentation.di.bindings.MainBroadcasterBindings
import ru.khinkal.locationNotifier.feature.main.presentation.di.bindings.MainDataBindings
import ru.khinkal.locationNotifier.feature.main.presentation.di.deps.MainDeps
import ru.khinkal.locationNotifier.feature.main.presentation.di.deps.MainDepsFactory

@DependencyGraph(
    bindingContainers = [
        MainDataBindings::class,
        MainBroadcasterBindings::class,
    ],
)
interface MainGraph {

    val goalGeoPointRepository: GoalGeoPointRepository
    val goalGeoPointBroadcaster: GoalGeoPointBroadcaster

    @DependencyGraph.Factory
    fun interface Factory {

        fun create(
            @Provides deps: MainDeps,
        ): MainGraph
    }
}

fun createMainGraph(
    deps: MainDeps = MainDepsFactory.INSTANCE.create(),
): MainGraph {
    return createGraphFactory<MainGraph.Factory>()
        .create(deps = deps)
}
