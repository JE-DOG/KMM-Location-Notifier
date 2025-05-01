package ru.khinkal.locationNotifier.feature.main.data

import ru.khinkal.locationNotifier.feature.main.data.storage.LocationStorageDataSource
import ru.khinkal.locationNotifier.feature.main.domain.LocationRepository
import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint

class LocationRepositoryImpl(
    private val storageDataSource: LocationStorageDataSource,
) : LocationRepository {

    override suspend fun getAllLocation(): List<GoalGeoPoint> {
        return storageDataSource.getAllLocation()
    }

    override suspend fun addLocation(goalGeoPoint: GoalGeoPoint) {
        storageDataSource.addLocation(goalGeoPoint)
    }

    override suspend fun updateLocation(goalGeoPoint: GoalGeoPoint) {
        storageDataSource.updateLocation(goalGeoPoint)
    }

    override suspend fun deleteLocation(goalGeoPoint: GoalGeoPoint) {
        storageDataSource.deleteLocation(goalGeoPoint)
    }
}