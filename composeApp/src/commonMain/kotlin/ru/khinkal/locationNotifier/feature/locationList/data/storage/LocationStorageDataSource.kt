package ru.khinkal.locationNotifier.feature.locationList.data.storage

import ru.khinkal.locationNotifier.feature.locationList.domain.model.GeoPoint

interface LocationStorageDataSource {

    suspend fun getAllLocation(): List<GeoPoint>

    suspend fun addLocation(geoPoint: GeoPoint)

    suspend fun updateLocation(geoPoint: GeoPoint)

    suspend fun deleteLocation(geoPoint: GeoPoint)
}