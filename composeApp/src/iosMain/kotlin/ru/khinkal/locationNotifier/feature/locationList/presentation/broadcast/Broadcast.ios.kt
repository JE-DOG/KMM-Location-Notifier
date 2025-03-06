package ru.khinkal.locationNotifier.feature.locationList.presentation.broadcast

import location.getLocationService
import notification.getNotificationService
import ru.khinkal.locationNotifier.core.location.model.BaseGeoPoint
import ru.khinkal.locationNotifier.core.ext.location.distanceInMeters
import ru.khinkal.locationNotifier.feature.locationList.domain.model.GeoPoint

actual fun startBroadcast(
    geoPoint: GeoPoint,
    locationUpdateSecondsInterval: Int,
    vibrationState: Boolean
) {
    val locationManager = getLocationService()
    val notificationManager = getNotificationService()
    val goalBaseGeoPoint = BaseGeoPoint(geoPoint)

    locationManager.startBroadcast(
        interval = locationUpdateSecondsInterval.toDouble(),
        onLocationUpdated = { baseGeoPoint ->
            val metersDiff = baseGeoPoint distanceInMeters goalBaseGeoPoint
            if (metersDiff < geoPoint.meters) {
                notificationManager.sendNotification(
                    id = geoPoint.id,
                    title = "You are get to ${geoPoint.name}",
                    description = "",
                    isSoundEnabled = true,
                )
                locationManager.stopBroadcast()
            } else {
                notificationManager.sendNotification(
                    id = geoPoint.id,
                    title = geoPoint.name,
                    description = "Meters: $metersDiff",
                    isSoundEnabled = false,
                )
            }
        },
    )
}
