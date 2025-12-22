package ru.khinkal.locationNotifier.core.location

import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExportObjCClass
import platform.CoreLocation.CLAuthorizationStatus
import platform.CoreLocation.CLLocation
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.CLLocationManagerDelegateProtocol
import platform.Foundation.NSError
import platform.darwin.NSObject

@OptIn(BetaInteropApi::class)
@ExportObjCClass
class LocationManagerDelegate(
    private val onLocationUpdate: (CLLocation) -> Unit = {},
    private val onAuthorizationChange: (CLAuthorizationStatus) -> Unit = {},
    private val onLocationFail: (NSError) -> Unit = {},
) : NSObject(), CLLocationManagerDelegateProtocol {

    @Suppress("UNCHECKED_CAST")
    override fun locationManager(manager: CLLocationManager, didUpdateLocations: List<*>) {
        val locations = didUpdateLocations as? List<CLLocation> ?: return
        val latestLocation = locations.lastOrNull() ?: return
        onLocationUpdate(latestLocation)
    }

    override fun locationManager(
        manager: CLLocationManager,
        didChangeAuthorizationStatus: CLAuthorizationStatus
    ) {
        onAuthorizationChange(didChangeAuthorizationStatus)
    }

    override fun locationManager(manager: CLLocationManager, didFailWithError: NSError) {
        onLocationFail(didFailWithError)
    }
}
