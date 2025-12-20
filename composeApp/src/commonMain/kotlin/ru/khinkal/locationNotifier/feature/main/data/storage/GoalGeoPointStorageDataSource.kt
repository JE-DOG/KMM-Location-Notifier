package ru.khinkal.locationNotifier.feature.main.data.storage

import kotlinx.coroutines.flow.Flow
import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint

interface GoalGeoPointStorageDataSource {

    fun getAll(): Flow<List<GoalGeoPoint>>

    suspend fun add(goalGeoPoint: GoalGeoPoint)

    suspend fun update(goalGeoPoint: GoalGeoPoint)

    suspend fun delete(goalGeoPoint: GoalGeoPoint)
}
