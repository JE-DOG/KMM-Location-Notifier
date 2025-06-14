package ru.khinkal.locationNotifier.feature.main.data.storage

import ru.khinkal.locationNotifier.feature.main.data.storage.model.GoalGeoPointEntity
import ru.khinkal.locationNotifier.feature.main.data.storage.room.GoalGeoPointDao
import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint

class GoalGeoPointStorageDataSourceImpl(
    private val locationListDao: GoalGeoPointDao,
) : GoalGeoPointStorageDataSource {

    override suspend fun getAll(): List<GoalGeoPoint> =
        locationListDao.getAll()
            .map { geoPointEntity ->
                geoPointEntity.toDomain()
            }

    override suspend fun add(goalGeoPoint: GoalGeoPoint) {
        locationListDao.add(GoalGeoPointEntity.fromDomain(goalGeoPoint))
    }

    override suspend fun update(goalGeoPoint: GoalGeoPoint) {
        locationListDao.update(GoalGeoPointEntity.fromDomain(goalGeoPoint))
    }

    override suspend fun delete(goalGeoPoint: GoalGeoPoint) {
        locationListDao.delete(goalGeoPoint.id)
    }
}
