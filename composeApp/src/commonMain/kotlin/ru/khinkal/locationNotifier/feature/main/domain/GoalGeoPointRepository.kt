package ru.khinkal.locationNotifier.feature.main.domain

import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint

interface GoalGeoPointRepository {

    suspend fun getAll(): List<GoalGeoPoint>

    suspend fun add(goalGeoPoint: GoalGeoPoint)

    suspend fun update(goalGeoPoint: GoalGeoPoint)

    suspend fun delete(goalGeoPoint: GoalGeoPoint)
}