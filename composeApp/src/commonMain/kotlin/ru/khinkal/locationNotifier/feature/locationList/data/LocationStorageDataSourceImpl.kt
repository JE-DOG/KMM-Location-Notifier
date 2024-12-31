package ru.khinkal.locationNotifier.feature.locationList.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.khinkal.locationNotifier.feature.locationList.data.model.GeoPointEntity
import ru.khinkal.locationNotifier.feature.locationList.data.room.LocationDao
import ru.khinkal.locationNotifier.feature.locationList.domain.model.GeoPoint

class LocationStorageDataSourceImpl(
    private val locationListDao: LocationDao
): LocationStorageDataSource {

    override fun getAllLocation(): Flow<List<GeoPoint>> = flow {
        val result = locationListDao.getAllLocation()
            .map { geoPointEntity ->
                geoPointEntity.toDomain()
            }
        emit(result)
    }

    override suspend fun addLocation(geoPoint: GeoPoint) {
        locationListDao.addLocation(GeoPointEntity.fromDomain(geoPoint))
    }

    override suspend fun updateLocation(geoPoint: GeoPoint) {
        locationListDao.updateLocation(GeoPointEntity.fromDomain(geoPoint))
    }

    override suspend fun deleteLocation(geoPoint: GeoPoint) {
        locationListDao.deleteLocation(geoPoint.id)
    }

    override suspend fun deleteAllLocation() {
        locationListDao.deleteAllLocation()
    }
}