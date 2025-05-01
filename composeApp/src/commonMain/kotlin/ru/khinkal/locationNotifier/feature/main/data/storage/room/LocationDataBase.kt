package ru.khinkal.locationNotifier.feature.main.data.storage.room

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import kmp.room.locations.LocationDataBaseConstructor
import ru.khinkal.locationNotifier.feature.main.data.storage.model.GeoPointEntity

@Database(
    entities = [GeoPointEntity::class],
    version = 1,
)
@ConstructedBy(LocationDataBaseConstructor::class)
abstract class LocationDataBase : RoomDatabase() {

    abstract fun locationDao(): LocationDao

    companion object {

        const val TABLE_NAME = "location_table"
    }
}
