package ru.khinkal.locationNotifier.feature.main.data.storage

import ru.khinkal.locationNotifier.feature.main.domain.model.GeoPoint

interface LocationStorageDataSource {

    suspend fun getAllLocation(): List<GeoPoint>

    suspend fun addLocation(geoPoint: GeoPoint)

    suspend fun updateLocation(geoPoint: GeoPoint)

    suspend fun deleteLocation(geoPoint: GeoPoint)
}
