package ru.khinkal.locationNotifier.feature.main.data.storage.room

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import kmp.room.locations.AppDataBaseConstructor
import ru.khinkal.locationNotifier.feature.main.data.storage.model.GoalGeoPointEntity

@Database(
    entities = [GoalGeoPointEntity::class],
    version = 1,
)
@ConstructedBy(AppDataBaseConstructor::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun goalGeoPointDao(): GoalGeoPointDao

    companion object {

        const val TABLE_NAME = "location_table"
    }
}
