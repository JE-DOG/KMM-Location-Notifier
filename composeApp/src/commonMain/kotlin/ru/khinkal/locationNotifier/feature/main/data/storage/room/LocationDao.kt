package ru.khinkal.locationNotifier.feature.main.data.storage.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ru.khinkal.locationNotifier.feature.main.data.storage.model.GoalGeoPointEntity

@Dao
interface LocationDao {

    @Query("SELECT * FROM geo_point_table")
    suspend fun getAllLocation(): List<GoalGeoPointEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLocation(geoPoint: GoalGeoPointEntity)

    @Update(entity = GoalGeoPointEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateLocation(geoPoint: GoalGeoPointEntity)

    @Query("DELETE FROM geo_point_table WHERE id = :geoPointId")
    suspend fun deleteLocation(geoPointId: Int): Int
}
