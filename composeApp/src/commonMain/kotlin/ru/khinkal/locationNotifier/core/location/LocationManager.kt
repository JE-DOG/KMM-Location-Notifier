package ru.khinkal.locationNotifier.core.location

import kotlinx.coroutines.flow.Flow
import ru.khinkal.locationNotifier.core.location.model.BaseGeoPoint

interface LocationManager {

    fun gpsStatusBroadcast(): Flow<Boolean>

    suspend fun getCurrentLocation(): BaseGeoPoint?

    fun broadcastLocation(updateSeconds: Int): Flow<BaseGeoPoint>
}