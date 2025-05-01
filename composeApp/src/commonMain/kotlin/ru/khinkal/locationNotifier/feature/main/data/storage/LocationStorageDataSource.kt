package ru.khinkal.locationNotifier.feature.main.data.storage

import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint

interface LocationStorageDataSource {

    suspend fun getAllLocation(): List<GoalGeoPoint>

    suspend fun addLocation(goalGeoPoint: GoalGeoPoint)

    suspend fun updateLocation(goalGeoPoint: GoalGeoPoint)

    suspend fun deleteLocation(goalGeoPoint: GoalGeoPoint)
}
