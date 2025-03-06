package ru.khinkal.locationNotifier.feature.locationList.data.storage.room

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import ru.khinkal.locationNotifier.feature.locationList.data.storage.model.GeoPointEntity

@Database(
    entities = [
        GeoPointEntity::class,
    ],
    version = 1
)
@ConstructedBy(LocationDataBaseConstructor::class)
abstract class LocationDataBase: RoomDatabase() {

    abstract fun locationDao(): LocationDao
}

expect fun getDatabaseBuilder(): RoomDatabase.Builder<LocationDataBase>

fun getDatabase(): LocationDataBase {
    return getDatabaseBuilder()
        .setDriver(BundledSQLiteDriver())
        .fallbackToDestructiveMigration(true)
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}

// Room во время компиляции реализует
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object LocationDataBaseConstructor : RoomDatabaseConstructor<LocationDataBase> {
    override fun initialize(): LocationDataBase
}
