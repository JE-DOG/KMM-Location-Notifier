package ru.khinkal.locationNotifier.feature.main.domain

import kotlinx.coroutines.flow.Flow
import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint

interface GoalGeoPointRepository {

    fun getAll(): Flow<List<GoalGeoPoint>>

    suspend fun add(goalGeoPoint: GoalGeoPoint)

    suspend fun update(goalGeoPoint: GoalGeoPoint)

    suspend fun delete(goalGeoPoint: GoalGeoPoint)
}