package ru.khinkal.locationNotifier.core.location

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Looper
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import ru.khinkal.locationNotifier.core.location.model.BaseGeoPoint
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LocationManagerImpl(
    private val context: Context,
): LocationManager {

    private val fusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }
    private val locationService by lazy {
        context.getSystemService(Context.LOCATION_SERVICE) as android.location.LocationManager
    }

    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): BaseGeoPoint? = suspendCoroutine { continuation ->
        fusedLocationProviderClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            CancellationTokenSource().token
        )
            .addOnSuccessListener { location ->
                val baseLocation = BaseGeoPoint(
                    longitude = location.longitude,
                    latitude = location.latitude,
                )

                continuation.resume(baseLocation)
            }
            .addOnFailureListener {
                continuation.resume(null)
            }
    }

    @SuppressLint("MissingPermission")
    override fun gpsStatusBroadcast(): Flow<Boolean> = callbackFlow {
        send(gpsIsEnabled())

        val gpsStateReceiver = object: BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                if (intent.action == android.location.LocationManager.PROVIDERS_CHANGED_ACTION){
                    trySend(gpsIsEnabled())
                }
            }
        }
        context.registerReceiver(
            gpsStateReceiver,
            IntentFilter(android.location.LocationManager.PROVIDERS_CHANGED_ACTION)
        )

        awaitClose {
            context.unregisterReceiver(gpsStateReceiver)
        }
    }

    @SuppressLint("MissingPermission")
    override fun broadcastLocation(
        updateSeconds: Int,
    ): Flow<BaseGeoPoint> = callbackFlow {
        val scope = CoroutineScope(coroutineContext)
        val locationCallback = object : LocationCallback(){
            override fun onLocationResult(locationResult: LocationResult) {
                scope.launch {
                    locationResult.lastLocation?.let { location ->
                        val geoPoint = BaseGeoPoint(
                            latitude = location.latitude,
                            longitude = location.longitude
                        )

                        send(geoPoint)
                    }
                }
            }
        }

        fusedLocationProviderClient.requestLocationUpdates(
            LocationRequest.Builder(updateSeconds * 1000L).apply {
                setPriority(Priority.PRIORITY_HIGH_ACCURACY)
            }.build(),
            locationCallback,
            Looper.getMainLooper()
        )

        awaitClose {
            fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        }
    }

    private fun gpsIsEnabled(): Boolean {
        return locationService.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)
    }
}

