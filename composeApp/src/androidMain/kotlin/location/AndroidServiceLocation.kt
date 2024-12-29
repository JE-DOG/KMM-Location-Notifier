package location

actual fun getLocationService(): LocationService = object : LocationService {
    override fun startUpdatingLocation(interval: Double) {
        TODO("Not yet implemented")
    }

    override fun stopUpdatingLocation() {
        TODO("Not yet implemented")
    }
}