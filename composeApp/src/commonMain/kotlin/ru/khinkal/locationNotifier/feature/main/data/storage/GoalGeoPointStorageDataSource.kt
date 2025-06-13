package ru.khinkal.locationNotifier.feature.main.data.storage

import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint

interface GoalGeoPointStorageDataSource {

    suspend fun getAll(): List<GoalGeoPoint>

    suspend fun add(goalGeoPoint: GoalGeoPoint)

    suspend fun update(goalGeoPoint: GoalGeoPoint)

    suspend fun delete(goalGeoPoint: GoalGeoPoint)
}
