package ru.khinkal.locationNotifier.feature.goalBroadcaster.di

import kmp.core.deps.EmptySystemDeps
import kmp.core.deps.SystemDeps
import kotlinx.coroutines.CoroutineScope
import ru.khinkal.locationNotifier.core.di.CoreModule
import ru.khinkal.locationNotifier.core.location.LocationService
import ru.khinkal.locationNotifier.core.location.di.LocationServiceModule
import ru.khinkal.locationNotifier.core.notification.NotificationService
import ru.khinkal.locationNotifier.core.notification.di.NotificationServiceModule
import ru.khinkal.locationNotifier.core.vibration.di.VibrationServiceModule
import ru.khinkal.locationNotifier.feature.settings.data.di.SettingsDataModule
import ru.khinkal.locationNotifier.feature.settings.domain.SettingsManager

class GoalGeoPointBroadcasterComponent(
    val coroutineScope: CoroutineScope,
    val systemDeps: SystemDeps = EmptySystemDeps(),
) {

    private val settingsDataModule by lazy { SettingsDataModule() }
    private val locationServiceModule by lazy { LocationServiceModule() }
    private val notificationModule by lazy { NotificationServiceModule() }
    private val vibrationServiceModule by lazy { VibrationServiceModule() }

    val vibrationService by lazy {
        vibrationServiceModule.provideVibrationService(systemDeps)
    }
    val notificationService: NotificationService by lazy {
        notificationModule.provideNotificationService(
            systemDeps = systemDeps,
        )
    }
    val locationService: LocationService by lazy {
        locationServiceModule.provideLocationService(
            systemDeps = systemDeps,
            coroutineScope = coroutineScope,
        )
    }
    val settingsManager: SettingsManager by lazy {
        val pathManager = CoreModule().providePathManager(systemDeps)

        settingsDataModule.provideSettingsManager(
            pathManager = pathManager,
        )
    }
}
