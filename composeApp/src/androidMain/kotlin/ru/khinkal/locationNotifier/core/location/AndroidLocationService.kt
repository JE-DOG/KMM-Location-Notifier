package ru.khinkal.locationNotifier.core.location

import android.Manifest
import android.content.Context
import android.os.Looper
import androidx.annotation.RequiresPermission
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.CoroutineScope
import ru.khinkal.locationNotifier.core.location.model.GeoPoint

class AndroidLocationService(
    private val context: Context,
    coroutineScope: CoroutineScope,
) : LocationService(
    coroutineScope = coroutineScope,
) {

    private var locationCallback: LocationCallback? = null
    private val fusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }

    @RequiresPermission(
        allOf = [
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        ],
    )
    override fun startBroadcast() {
        fusedLocationProviderClient.startBroadcast(
            onLocationUpdated = ::updateLocation,
        )
    }

    @RequiresPermission(
        allOf = [
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        ],
    )
    private fun FusedLocationProviderClient.startBroadcast(
        onLocationUpdated: (GeoPoint) -> Unit,
    ) {
        val locationCallback = createLocationCallback(onLocationUpdated)
            .also { locationCallback = it }
        val locationRequest = createLocationRequest(10)

        requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper(),
        )
    }

    private fun createLocationRequest(
        secondsInterval: Int,
        priority: Int = Priority.PRIORITY_HIGH_ACCURACY,
    ): LocationRequest {

        return LocationRequest.Builder(secondsInterval * 1000L).apply {
            setPriority(priority)
        }
            .build()
    }

    private fun createLocationCallback(
        onLocationUpdated: (GeoPoint) -> Unit,
    ): LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val location = locationResult.lastLocation ?: return
            val geoPoint = GeoPoint(
                latitude = location.latitude,
                longitude = location.longitude
            )

            onLocationUpdated(geoPoint)
        }
    }

    override fun stopBroadcast() {
        val locationCallback = locationCallback ?: return
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        this.locationCallback = null
    }
}
