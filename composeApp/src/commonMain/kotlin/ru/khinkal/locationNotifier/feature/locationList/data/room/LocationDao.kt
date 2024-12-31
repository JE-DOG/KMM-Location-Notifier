package ru.khinkal.locationNotifier.feature.locationList.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ru.khinkal.locationNotifier.feature.locationList.data.model.GeoPointEntity

@Dao
interface LocationDao {

    @Query("SELECT * FROM geo_point_table")
    fun getAllLocation(): List<GeoPointEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLocation(geoPoint: GeoPointEntity)

    @Update(entity = GeoPointEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateLocation(geoPoint: GeoPointEntity)

    @Query("DELETE FROM geo_point_table WHERE id = :geoPointId")
    suspend fun deleteLocation(geoPointId: Int): Int

    @Query("DELETE FROM geo_point_table")
    suspend fun deleteAllLocation()
}
