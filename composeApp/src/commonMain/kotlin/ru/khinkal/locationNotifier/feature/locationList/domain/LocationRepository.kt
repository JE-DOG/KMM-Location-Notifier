package ru.khinkal.locationNotifier.feature.locationList.domain

import ru.khinkal.locationNotifier.feature.locationList.domain.model.GeoPoint

interface LocationRepository {

    suspend fun getAllLocation(): List<GeoPoint>

    suspend fun addLocation(geoPoint: GeoPoint): Boolean

    suspend fun updateLocation(geoPoint: GeoPoint): Boolean

    suspend fun deleteLocation(geoPoint: GeoPoint): Boolean

    suspend fun deleteAllLocation(): Boolean
}