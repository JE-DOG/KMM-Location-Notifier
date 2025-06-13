package ru.khinkal.locationNotifier.feature.main.data

import ru.khinkal.locationNotifier.feature.main.data.storage.GoalGeoPointStorageDataSource
import ru.khinkal.locationNotifier.feature.main.domain.GoalGeoPointRepository
import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint

class GoalGeoPointRepositoryImpl(
    private val storageDataSource: GoalGeoPointStorageDataSource,
) : GoalGeoPointRepository {

    override suspend fun getAll(): List<GoalGeoPoint> {
        return storageDataSource.getAll()
    }

    override suspend fun add(goalGeoPoint: GoalGeoPoint) {
        storageDataSource.add(goalGeoPoint)
    }

    override suspend fun update(goalGeoPoint: GoalGeoPoint) {
        storageDataSource.update(goalGeoPoint)
    }

    override suspend fun delete(goalGeoPoint: GoalGeoPoint) {
        storageDataSource.delete(goalGeoPoint)
    }
}