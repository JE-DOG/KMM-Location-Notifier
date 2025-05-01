package kmp.core.location

import ru.khinkal.locationNotifier.core.location.model.GeoPoint

expect fun getLocationService(): LocationServiceVariants

interface LocationServiceVariants {
    fun startBroadcast(
        interval: Double,
        onLocationUpdated: (GeoPoint) -> Unit
    )

    fun stopBroadcast()
}
