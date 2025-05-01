package ru.khinkal.locationNotifier.feature.main.presentation.broadcast

import kmp.core.location.getLocationService
import kmp.core.notification.getNotificationService
import kmp.core.path.PathManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import ru.khinkal.locationNotifier.core.di.CoreModule
import ru.khinkal.locationNotifier.core.ext.coroutines.launchCatching
import ru.khinkal.locationNotifier.core.ext.location.distanceInMeters
import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint
import ru.khinkal.locationNotifier.feature.settings.domain.SettingsManager
import ru.khinkal.locationNotifier.feature.settings.presentation.di.SettingsComponent
import ru.khinkal.locationNotifier.feature.settings.presentation.di.deps.SettingsDeps

actual fun startBroadcast(goalGeoPoint: GoalGeoPoint) {
    val coroutineScope = CoroutineScope(Dispatchers.Main)

    coroutineScope.launchCatching {
        val settingsManager = getSettingsManager()
        val locationManager = getLocationService()
        val notificationManager = getNotificationService()
        val updateInterval = settingsManager.getLocationUpdateSeconds().toDouble()

        locationManager.startBroadcast(
            interval = updateInterval,
            onLocationUpdated = { baseGeoPoint ->
                launchCatching {
                    val metersDiff = baseGeoPoint distanceInMeters goalGeoPoint.geoPoint
                    if (metersDiff < goalGeoPoint.meters) {
                        notificationManager.sendNotification(
                            id = goalGeoPoint.id,
                            title = "You are get to ${goalGeoPoint.name}",
                            isSoundEnabled = true,
                        )
                        locationManager.stopBroadcast()
                    } else {
                        notificationManager.sendNotification(
                            id = goalGeoPoint.id,
                            title = goalGeoPoint.name,
                            description = "Meters: $metersDiff",
                            isSoundEnabled = settingsManager.isVibrationEnabled(),
                        )
                    }
                }
            },
        )
    }
}

private fun getSettingsManager(): SettingsManager {
    val coreModule = CoreModule()
    val settingsDeps = object : SettingsDeps {
        override val pathManager: PathManager
            get() = coreModule.providePathManager()
    }
    return SettingsComponent(settingsDeps).settingsManager
}
