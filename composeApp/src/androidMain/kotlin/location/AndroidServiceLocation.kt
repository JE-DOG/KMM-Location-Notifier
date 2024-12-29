package location

import ru.khinkal.locationNotifier.core.location.model.BaseGeoPoint

actual fun getLocationService(): LocationServiceVariants = object : LocationServiceVariants {
    override fun startBroadcast(
        interval: Double,
        onLocationUpdated: (BaseGeoPoint) -> Unit,
    ) {
        TODO("Not yet implemented")
    }

    override fun stopBroadcast() {
        TODO("Not yet implemented")
    }
}