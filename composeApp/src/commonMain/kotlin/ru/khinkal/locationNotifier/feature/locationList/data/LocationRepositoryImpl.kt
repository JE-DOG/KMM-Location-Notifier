package ru.khinkal.locationNotifier.feature.locationList.data

import ru.khinkal.locationNotifier.feature.locationList.data.storage.LocationStorageDataSource
import ru.khinkal.locationNotifier.feature.locationList.domain.LocationRepository
import ru.khinkal.locationNotifier.feature.locationList.domain.model.GeoPoint

class LocationRepositoryImpl(
    private val storageDataSource: LocationStorageDataSource,
): LocationRepository {

    override suspend fun getAllLocation(): List<GeoPoint> {
        return storageDataSource.getAllLocation()
    }

    override suspend fun addLocation(geoPoint: GeoPoint) {
        storageDataSource.addLocation(geoPoint)
    }

    override suspend fun updateLocation(geoPoint: GeoPoint) {
        storageDataSource.updateLocation(geoPoint)
    }

    override suspend fun deleteLocation(geoPoint: GeoPoint) {
        storageDataSource.deleteLocation(geoPoint)
    }
}