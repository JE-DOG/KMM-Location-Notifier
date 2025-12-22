package kmp.core.storage.room

import androidx.room.Room
import androidx.room.RoomDatabase
import kmp.core.deps.SystemDeps
import kmp.core.asAndroid

actual object Room {

    actual inline fun <reified RD : RoomDatabase> databaseBuilder(
        systemDeps: SystemDeps,
        name: String
    ): RoomDatabase.Builder<RD> = Room.databaseBuilder(
        context = systemDeps.asAndroid().applicationContext,
        name = name,
    )
}
