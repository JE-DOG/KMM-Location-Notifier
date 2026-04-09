package ru.khinkal.locationNotifier.feature.goalBroadcaster.di

import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.createGraphFactory
import ru.khinkal.locationNotifier.core.location.LocationService
import ru.khinkal.locationNotifier.core.notification.NotificationService
import ru.khinkal.locationNotifier.core.vibration.VibrationService
import ru.khinkal.locationNotifier.feature.goalBroadcaster.di.GoalGeoPointBroadcasterGraph.Factory
import ru.khinkal.locationNotifier.feature.goalBroadcaster.di.bindings.GoalGeoPointBroadcasterBindings
import ru.khinkal.locationNotifier.feature.goalBroadcaster.di.deps.GoalGeoPointBroadcasterDeps

@DependencyGraph(
    bindingContainers = [
        GoalGeoPointBroadcasterBindings::class,
    ],
)
interface GoalGeoPointBroadcasterGraph {

    val locationService: LocationService
    val notificationService: NotificationService
    val vibrationService: VibrationService

    @DependencyGraph.Factory
    fun interface Factory {

        fun create(
            @Provides deps: GoalGeoPointBroadcasterDeps,
        ): GoalGeoPointBroadcasterGraph
    }
}

fun createGoalGeoPointBroadcasterGraph(
    deps: GoalGeoPointBroadcasterDeps,
): GoalGeoPointBroadcasterGraph {
    return createGraphFactory<Factory>()
        .create(deps = deps)
}
