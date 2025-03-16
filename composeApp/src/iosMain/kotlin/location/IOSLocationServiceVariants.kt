package location

import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ExportObjCClass
import kotlinx.cinterop.MemScope
import kotlinx.cinterop.pointed
import platform.CoreLocation.CLAuthorizationStatus
import platform.CoreLocation.CLLocation
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.CLLocationManagerDelegateProtocol
import platform.CoreLocation.kCLAuthorizationStatusAuthorizedAlways
import platform.CoreLocation.kCLAuthorizationStatusAuthorizedWhenInUse
import platform.CoreLocation.kCLAuthorizationStatusDenied
import platform.CoreLocation.kCLAuthorizationStatusNotDetermined
import platform.CoreLocation.kCLAuthorizationStatusRestricted
import platform.CoreLocation.kCLLocationAccuracyBest
import platform.Foundation.NSError
import platform.Foundation.NSRunLoop
import platform.Foundation.NSRunLoopCommonModes
import platform.Foundation.NSTimer
import platform.darwin.NSObject
import ru.khinkal.locationNotifier.core.location.model.BaseGeoPoint

class IOSLocationService : LocationServiceVariants {

    private val locationManager = CLLocationManager()
    private var timer: NSTimer? = null
    private var interval: Double = 0.0

    @OptIn(ExperimentalForeignApi::class)
    private val delegate = LocationManagerDelegate(
        onLocationUpdate = { location ->
            // Обработка полученного местоположения
            println("Updated location: ${location.coordinate}, ${location.coordinate}")
        },
        onAuthorizationChange = { status ->
            when (status) {
                kCLAuthorizationStatusAuthorizedAlways -> {
                    println("Authorization granted: Always")
                }

                kCLAuthorizationStatusAuthorizedWhenInUse -> {
                    println("Authorization granted: When In Use")
                    // Если нужно, можно запросить Always
                    locationManager.requestAlwaysAuthorization()
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

    init {
        // Привязываем делегат и настраиваем CLLocationManager
        locationManager.delegate = delegate
        locationManager.desiredAccuracy = kCLLocationAccuracyBest
        locationManager.allowsBackgroundLocationUpdates = true
        locationManager.pausesLocationUpdatesAutomatically = false
    }

    @OptIn(ExperimentalForeignApi::class)
    override fun startBroadcast(
        interval: Double,
        onLocationUpdated: (BaseGeoPoint) -> Unit,
    ) {
        this.interval = interval

        // Запрашиваем разрешение (Always или WhenInUse — под ваши нужды)
        locationManager.requestAlwaysAuthorization()

        // Начинаем трекать локацию
        locationManager.startUpdatingLocation()

        // Если нужно периодически что-то делать раз в N секунд
        timer = NSTimer.scheduledTimerWithTimeInterval(
            interval = interval,
            repeats = true,
            block = { _ ->
                val location = locationManager.location ?: return@scheduledTimerWithTimeInterval
                val coordinate = location.coordinate.getPointer(MemScope())
                val point = coordinate.pointed
                onLocationUpdated(
                    BaseGeoPoint(
                        latitude = point.latitude,
                        longitude = point.longitude,
                    )
                )
                println("Timer tick: lat=${point.latitude}, lon=${point.longitude}")
            }
        )
        NSRunLoop.mainRunLoop.addTimer(timer!!, NSRunLoopCommonModes)
    }

    override fun stopBroadcast() {
        locationManager.stopUpdatingLocation()
        stopTimer()
    }

    private fun stopTimer() {
        timer?.invalidate()
        timer = null
    }
}

@OptIn(BetaInteropApi::class)
@ExportObjCClass
class LocationManagerDelegate(
    private val onLocationUpdate: (CLLocation) -> Unit,
    private val onAuthorizationChange: (CLAuthorizationStatus) -> Unit,
    private val onLocationFail: (NSError) -> Unit
) : NSObject(), CLLocationManagerDelegateProtocol {

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

actual fun getLocationService(): LocationServiceVariants = IOSLocationService()
