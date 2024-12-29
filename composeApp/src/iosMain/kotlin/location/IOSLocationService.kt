package location

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ExportObjCClass
import platform.CoreLocation.*
import platform.Foundation.*
import platform.darwin.*

@ExportObjCClass
class LocationManagerDelegate(
    private val onLocationUpdate: (CLLocation) -> Unit,
    private val onAuthorizationChange: (CLAuthorizationStatus) -> Unit
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
}

class IOSLocationService : LocationService {

    private val locationManager = CLLocationManager()
    private var timer: NSTimer? = null
    private var interval: Double = 0.0

    @OptIn(ExperimentalForeignApi::class)
    private val delegate = LocationManagerDelegate(
        onLocationUpdate = { location ->
            // Обработка полученного местоположения
            // Например, сохраняем или отправляем данные
            println("Updated location: ${location.coordinate}, ${location.coordinate}")
        },
        onAuthorizationChange = { status ->
            when (status) {
                kCLAuthorizationStatusAuthorizedAlways -> {
                    // Пользователь предоставил доступ
                    println("Authorization granted: Always")
                }

                kCLAuthorizationStatusDenied, kCLAuthorizationStatusRestricted -> {
                    // Доступ отклонен
                    println("Authorization denied or restricted")
                }

                else -> {
                    // Другие случаи
                }
            }
        }
    )

    init {
        locationManager.delegate = delegate
        locationManager.desiredAccuracy = kCLLocationAccuracyBest
        locationManager.allowsBackgroundLocationUpdates = true
        locationManager.pausesLocationUpdatesAutomatically = false
    }

    override fun startUpdatingLocation(interval: Double) {
        this.interval = interval
        locationManager.requestAlwaysAuthorization()
        locationManager.startUpdatingLocation()
        startTimer()
    }

    override fun stopUpdatingLocation() {
        locationManager.stopUpdatingLocation()
        stopTimer()
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun startTimer() {
        timer = NSTimer.scheduledTimerWithTimeInterval(
            interval = interval,
            repeats = true,
            block = { _ ->
                // Ваш код для обработки местоположения каждые N секунд
                val location = locationManager.location
                if (location != null) {
                    println("Timer tick: ${location.coordinate}, ${location.coordinate}")
                    // Дополнительная обработка
                }
            }
        )
        NSRunLoop.mainRunLoop.addTimer(timer!!, NSRunLoopCommonModes)
    }

    private fun stopTimer() {
        timer?.invalidate()
        timer = null
    }
}

actual fun getLocationService(): LocationService = IOSLocationService()
