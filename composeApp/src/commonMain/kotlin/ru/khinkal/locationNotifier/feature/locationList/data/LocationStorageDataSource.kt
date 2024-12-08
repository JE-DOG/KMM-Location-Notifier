package ru.khinkal.locationNotifier.feature.locationList.data

import kotlinx.coroutines.flow.Flow
import ru.khinkal.locationNotifier.feature.locationList.domain.model.GeoPoint

interface LocationStorageDataSource {

    fun getAllLocation(): Flow<List<GeoPoint>>

    suspend fun addLocation(geoPoint: GeoPoint)

    suspend fun updateLocation(geoPoint: GeoPoint)

    suspend fun deleteLocation(geoPoint: GeoPoint)

    suspend fun deleteAllLocation()
}