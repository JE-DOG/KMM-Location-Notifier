package ru.khinkal.locationNotifier.feature.main.data.storage

import ru.khinkal.locationNotifier.feature.main.data.storage.model.GoalGeoPointEntity
import ru.khinkal.locationNotifier.feature.main.data.storage.room.LocationDao
import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint

class LocationStorageDataSourceImpl(
    private val locationListDao: LocationDao,
) : LocationStorageDataSource {

    override suspend fun getAllLocation(): List<GoalGeoPoint> =
        locationListDao.getAllLocation()
            .map { geoPointEntity ->
                geoPointEntity.toDomain()
            }

    override suspend fun addLocation(goalGeoPoint: GoalGeoPoint) {
        locationListDao.addLocation(GoalGeoPointEntity.fromDomain(goalGeoPoint))
    }

    override suspend fun updateLocation(goalGeoPoint: GoalGeoPoint) {
        locationListDao.updateLocation(GoalGeoPointEntity.fromDomain(goalGeoPoint))
    }

    override suspend fun deleteLocation(goalGeoPoint: GoalGeoPoint) {
        locationListDao.deleteLocation(goalGeoPoint.id)
    }
}
