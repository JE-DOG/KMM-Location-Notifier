package ru.khinkal.locationNotifier.feature.locationList.domain

import ru.khinkal.locationNotifier.feature.locationList.domain.model.GeoPoint

interface LocationRepository {

    suspend fun getAllLocation(): List<GeoPoint>

    suspend fun addLocation(geoPoint: GeoPoint)

    suspend fun updateLocation(geoPoint: GeoPoint)

    suspend fun deleteLocation(geoPoint: GeoPoint)
}