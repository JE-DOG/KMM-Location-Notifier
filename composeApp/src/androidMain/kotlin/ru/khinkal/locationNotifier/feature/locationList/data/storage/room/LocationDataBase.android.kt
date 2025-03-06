package ru.khinkal.locationNotifier.feature.locationList.data.storage.room

import androidx.room.Room
import androidx.room.RoomDatabase
import ru.khinkal.locationNotifier.LocationNotifierApp

actual fun getDatabaseBuilder(): RoomDatabase.Builder<LocationDataBase> {
    val appContext = LocationNotifierApp.INSTANCE
    val dbFile = appContext.getDatabasePath("app_database.db")
    return Room.databaseBuilder<LocationDataBase>(
        context = appContext,
        name = dbFile.absolutePath,
    )
}