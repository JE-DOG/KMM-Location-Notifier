package location

import ru.khinkal.locationNotifier.core.location.model.BaseGeoPoint

expect fun getLocationService(): LocationServiceVariants

interface LocationServiceVariants {
    fun startBroadcast(
        interval: Double,
        onLocationUpdated: (BaseGeoPoint) -> Unit
    )

    fun stopBroadcast()
}
