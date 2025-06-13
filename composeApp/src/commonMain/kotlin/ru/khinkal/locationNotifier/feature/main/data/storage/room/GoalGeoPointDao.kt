package ru.khinkal.locationNotifier.feature.main.data.storage.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ru.khinkal.locationNotifier.feature.main.data.storage.model.GoalGeoPointEntity

@Dao
interface GoalGeoPointDao {

    @Query("SELECT * FROM geo_point_table")
    suspend fun getAll(): List<GoalGeoPointEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(geoPoint: GoalGeoPointEntity)

    @Update(entity = GoalGeoPointEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(geoPoint: GoalGeoPointEntity)

    @Query("DELETE FROM geo_point_table WHERE id = :geoPointId")
    suspend fun delete(geoPointId: Int): Int
}
