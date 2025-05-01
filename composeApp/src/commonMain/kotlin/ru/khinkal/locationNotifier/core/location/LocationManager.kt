package ru.khinkal.locationNotifier.core.location

import kotlinx.coroutines.flow.Flow
import ru.khinkal.locationNotifier.core.location.model.GeoPoint

interface LocationManager {

    fun gpsStatusBroadcast(): Flow<Boolean>

    suspend fun getCurrentLocation(): GeoPoint?

    fun broadcastLocation(updateSeconds: Int): Flow<GeoPoint>
}
