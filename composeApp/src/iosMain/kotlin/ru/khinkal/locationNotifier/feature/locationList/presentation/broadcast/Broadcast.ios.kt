package ru.khinkal.locationNotifier.feature.locationList.presentation.broadcast

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import location.getLocationService
import notification.getNotificationService
import ru.khinkal.locationNotifier.core.ext.coroutines.launchCatching
import ru.khinkal.locationNotifier.core.ext.location.distanceInMeters
import ru.khinkal.locationNotifier.core.location.model.BaseGeoPoint
import ru.khinkal.locationNotifier.feature.locationList.domain.model.GeoPoint
import ru.khinkal.locationNotifier.feature.settings.data.factory.SettingsManagerImplFactory

actual fun startBroadcast(geoPoint: GeoPoint) {
    val coroutineScope = CoroutineScope(Dispatchers.Main)

    coroutineScope.launchCatching {
        val settingsManager = SettingsManagerImplFactory.create()
        val locationManager = getLocationService()
        val notificationManager = getNotificationService()
        val goalBaseGeoPoint = BaseGeoPoint(geoPoint)
        val updateInterval = settingsManager.getLocationUpdateSeconds().toDouble()

        locationManager.startBroadcast(
            interval = updateInterval,
            onLocationUpdated = { baseGeoPoint ->
                launchCatching {
                    val metersDiff = baseGeoPoint distanceInMeters goalBaseGeoPoint
                    if (metersDiff < geoPoint.meters) {
                        notificationManager.sendNotification(
                            id = geoPoint.id,
                            title = "You are get to ${geoPoint.name}",
                            isSoundEnabled = true,
                        )
                        locationManager.stopBroadcast()
                    } else {
                        notificationManager.sendNotification(
                            id = geoPoint.id,
                            title = geoPoint.name,
                            description = "Meters: $metersDiff",
                            isSoundEnabled = settingsManager.isVibrationEnabled(),
                        )
                    }
                }
            },
        )
    }
}
