package kmp.room.locations

import androidx.room.RoomDatabaseConstructor
import ru.khinkal.locationNotifier.feature.main.data.storage.room.AppDataBase

// Room create implementations by code generation
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDataBaseConstructor : RoomDatabaseConstructor<AppDataBase> {
    override fun initialize(): AppDataBase
}
