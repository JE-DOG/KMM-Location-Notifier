package kmp.core.storage.room

import androidx.room.Room
import androidx.room.RoomDatabase
import kmp.core.deps.SystemDeps

actual object Room {

    actual inline fun <reified RD : RoomDatabase> databaseBuilder(
        systemDeps: SystemDeps,
        name: String
    ): RoomDatabase.Builder<RD> = Room.databaseBuilder<RD>(name)
}
