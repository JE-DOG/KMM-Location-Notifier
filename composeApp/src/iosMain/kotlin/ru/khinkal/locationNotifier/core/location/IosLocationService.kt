package ru.khinkal.locationNotifier.core.location

import kotlinx.coroutines.CoroutineScope
import platform.CoreLocation.CLActivityTypeOtherNavigation
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.kCLAuthorizationStatusAuthorizedAlways
import platform.CoreLocation.kCLAuthorizationStatusAuthorizedWhenInUse
import platform.CoreLocation.kCLAuthorizationStatusDenied
import platform.CoreLocation.kCLAuthorizationStatusNotDetermined
import platform.CoreLocation.kCLAuthorizationStatusRestricted
import platform.CoreLocation.kCLLocationAccuracyHundredMeters
import ru.khinkal.locationNotifier.core.ext.coroutines.canceledJob
import ru.khinkal.locationNotifier.core.location.model.GeoPoint
import ru.khinkal.locationNotifier.core.location.util.toGeoPoint

class IosLocationService(
    coroutineScope: CoroutineScope,
) : LocationService(
    coroutineScope = coroutineScope,
) {

    private var timerJob by canceledJob()
    private val locationManager: CLLocationManager by lazy {
        CLLocationManager()
    }
    private val delegate: LocationManagerDelegate by lazy {
        createDelegate(::updateLocation)
    }

    init {
        locationManager.delegate = delegate
        locationManager.activityType = CLActivityTypeOtherNavigation
        locationManager.desiredAccuracy = kCLLocationAccuracyHundredMeters
        locationManager.allowsBackgroundLocationUpdates = true
        locationManager.pausesLocationUpdatesAutomatically = false
    }

    private fun createDelegate(
        onLocationUpdated: (GeoPoint) -> Unit = {},
    ): LocationManagerDelegate = LocationManagerDelegate(
        onLocationUpdate = { location ->
            val geoPoint = location.toGeoPoint()
            onLocationUpdated(geoPoint)
            println("Updated location: ${geoPoint.latitude}, ${geoPoint.longitude}")
        },
        onAuthorizationChange = { status ->
            when (status) {
                kCLAuthorizationStatusAuthorizedAlways -> {
                    println("Authorization granted: Always")
                }

                kCLAuthorizationStatusAuthorizedWhenInUse -> {
                    println("Authorization granted: When In Use")
                }

                kCLAuthorizationStatusDenied,
                kCLAuthorizationStatusRestricted -> {
                    println("Authorization denied or restricted")
                }

                kCLAuthorizationStatusNotDetermined -> {
                    println("Authorization not determined yet")
                }

                else -> {
                    println("Some other authorization status")
                }
            }
        },
        onLocationFail = { error ->
            println("Location update failed: ${error.localizedDescription}")
        },
    )

    override fun startBroadcast() {
        locationManager.requestWhenInUseAuthorization()
        getCurrentGeoPoint()?.let { updateLocation(it) }
        locationManager.startUpdatingLocation()
    }

    private fun getCurrentGeoPoint(): GeoPoint? {
        val location = locationManager.location ?: return null
        return location.toGeoPoint()
    }

    override fun stopBroadcast() {
        timerJob = null
        locationManager.stopUpdatingLocation()
    }
}
