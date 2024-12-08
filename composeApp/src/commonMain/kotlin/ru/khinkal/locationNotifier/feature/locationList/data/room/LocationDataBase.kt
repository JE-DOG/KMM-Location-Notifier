package ru.khinkal.locationNotifier.feature.locationList.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.khinkal.locationNotifier.feature.locationList.data.model.GeoPointEntity

@Database(
    entities = [
        GeoPointEntity::class,
    ],
    version = 1
)
abstract class LocationDataBase: RoomDatabase() {

    abstract fun locationDao(): LocationDao
}
