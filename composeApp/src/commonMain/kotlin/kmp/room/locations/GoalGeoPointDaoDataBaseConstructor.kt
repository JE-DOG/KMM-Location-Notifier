package kmp.room.locations

import androidx.room.RoomDatabaseConstructor
import ru.khinkal.locationNotifier.feature.main.data.storage.room.GoalGeoPointDaoDataBase

// Room create implementations by code generation
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object GoalGeoPointDaoDataBaseConstructor : RoomDatabaseConstructor<GoalGeoPointDaoDataBase> {
    override fun initialize(): GoalGeoPointDaoDataBase
}
