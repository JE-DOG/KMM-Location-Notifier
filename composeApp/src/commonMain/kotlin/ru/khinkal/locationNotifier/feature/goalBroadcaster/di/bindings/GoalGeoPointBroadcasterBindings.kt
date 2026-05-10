package ru.khinkal.locationNotifier.feature.goalBroadcaster.di.bindings

import dev.zacsweers.metro.BindingContainer
import dev.zacsweers.metro.Provides
import kmp.core.location.LocationServiceFactory
import kmp.core.notification.NotificationServiceFactory
import kmp.core.vibration.VibrationServiceFactory
import ru.khinkal.locationNotifier.core.location.LocationService
import ru.khinkal.locationNotifier.core.notification.NotificationService
import ru.khinkal.locationNotifier.core.vibration.VibrationService
import ru.khinkal.locationNotifier.feature.goalBroadcaster.di.deps.GoalGeoPointBroadcasterDeps

@BindingContainer
class GoalGeoPointBroadcasterBindings {

    @Provides
    fun provideLocationService(
        deps: GoalGeoPointBroadcasterDeps,
    ): LocationService {
        return LocationServiceFactory.createLocationService(
            systemDeps = deps.systemDeps,
            coroutineScope = deps.coroutineScope,
        )
    }

    @Provides
    fun provideNotificationService(
        deps: GoalGeoPointBroadcasterDeps,
    ): NotificationService {
        return NotificationServiceFactory.createNotificationService(
            systemDeps = deps.systemDeps,
        )
    }

    @Provides
    fun provideVibrationService(
        deps: GoalGeoPointBroadcasterDeps,
    ): VibrationService {
        return VibrationServiceFactory.create(
            systemDeps = deps.systemDeps,
        )
    }
}
