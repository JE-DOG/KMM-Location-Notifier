package kmp.room.locations

import androidx.room.RoomDatabaseConstructor
import ru.khinkal.locationNotifier.feature.main.data.storage.room.LocationDataBase

// Room create implementations by code generation
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object LocationDataBaseConstructor : RoomDatabaseConstructor<LocationDataBase> {
    override fun initialize(): LocationDataBase
}
