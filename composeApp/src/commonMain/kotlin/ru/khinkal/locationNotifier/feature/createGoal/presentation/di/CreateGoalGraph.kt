package ru.khinkal.locationNotifier.feature.createGoal.presentation.di

import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.createGraphFactory
import ru.khinkal.locationNotifier.feature.createGoal.presentation.di.CreateGoalGraph.Factory
import ru.khinkal.locationNotifier.feature.createGoal.presentation.di.bindings.CreateGoalDataBindings
import ru.khinkal.locationNotifier.feature.createGoal.presentation.di.deps.CreateGoalDeps
import ru.khinkal.locationNotifier.feature.createGoal.presentation.di.deps.CreateGoalDepsFactory
import ru.khinkal.locationNotifier.feature.main.domain.GoalGeoPointRepository

@DependencyGraph(
    bindingContainers = [
        CreateGoalDataBindings::class,
    ],
)
interface CreateGoalGraph {

    val goalGeoPointRepository: GoalGeoPointRepository

    @DependencyGraph.Factory
    fun interface Factory {

        fun create(
            @Provides deps: CreateGoalDeps,
        ): CreateGoalGraph
    }
}

fun createCreateGoalGraph(
    deps: CreateGoalDeps = CreateGoalDepsFactory.INSTANCE.create(),
): CreateGoalGraph {
    return createGraphFactory<Factory>()
        .create(deps = deps)
}
