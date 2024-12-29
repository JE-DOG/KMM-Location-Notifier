package location

expect fun getLocationService(): LocationService

interface LocationService {
    fun startUpdatingLocation(interval: Double)
    fun stopUpdatingLocation()
}
