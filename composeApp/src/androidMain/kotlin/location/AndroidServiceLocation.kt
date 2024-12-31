package location

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.khinkal.locationNotifier.LocationNotifierApp
import ru.khinkal.locationNotifier.core.location.LocationManagerImpl
import ru.khinkal.locationNotifier.core.location.model.BaseGeoPoint

actual fun getLocationService(): LocationServiceVariants = object : LocationServiceVariants {

    private val coroutineScope = CoroutineScope(SupervisorJob())
    private val application = LocationNotifierApp.INSTANCE
    private val locationManager by lazy { LocationManagerImpl(application) }
    private var locationBroadcastJob: Job? = null

    override fun startBroadcast(
        interval: Double,
        onLocationUpdated: (BaseGeoPoint) -> Unit,
    ) {
        locationBroadcastJob?.cancel()
        locationBroadcastJob = coroutineScope.launch {
            locationManager.broadcastLocation(
                interval.toInt(),
            )
                .collect { baseGeoPoint ->
                    onLocationUpdated(baseGeoPoint)
                }
        }
    }

    override fun stopBroadcast() {
        locationBroadcastJob?.cancel()
    }
}