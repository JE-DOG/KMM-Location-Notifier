package kmp.core.storage.room

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kmp.core.deps.SystemDeps
import kmp.core.path.PathManager
import ru.khinkal.locationNotifier.core.storage.room.util.RoomHelper
import ru.khinkal.locationNotifier.core.ext.absolutePath

expect object Room {

    inline fun <reified RD : RoomDatabase> databaseBuilder(
        systemDeps: SystemDeps,
        name: String
    ): RoomDatabase.Builder<RD>
}

inline fun <reified RD : RoomDatabase> roomDatabaseBuilder(
    name: String,
    pathManager: PathManager,
    systemDeps: SystemDeps,
): RoomDatabase.Builder<RD> {
    val fileName = RoomHelper.createFileName(name)
    val databasePath = pathManager.databasePath(fileName)

    return Room.databaseBuilder<RD>(
        systemDeps = systemDeps,
        name = databasePath.absolutePath,
    )
        .setDriver(BundledSQLiteDriver())
}
