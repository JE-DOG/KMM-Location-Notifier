package ru.khinkal.locationNotifier.feature.locationList.data.storage

import ru.khinkal.locationNotifier.feature.locationList.data.storage.model.GeoPointEntity
import ru.khinkal.locationNotifier.feature.locationList.data.storage.room.LocationDao
import ru.khinkal.locationNotifier.feature.locationList.domain.model.GeoPoint

class LocationStorageDataSourceImpl(
    private val locationListDao: LocationDao,
) : LocationStorageDataSource {

    override suspend fun getAllLocation(): List<GeoPoint> =
        locationListDao.getAllLocation()
            .map { geoPointEntity ->
                geoPointEntity.toDomain()
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
}