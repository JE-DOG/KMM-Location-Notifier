package ru.khinkal.locationNotifier.di.metro.bindings

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.BindingContainer
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn
import kmp.core.deps.SystemDeps
import kmp.core.path.PathManager
import kmp.core.path.createPathHelper
import kmp.core.storage.room.roomDatabaseBuilder
import ru.khinkal.locationNotifier.feature.main.data.storage.room.AppDataBase

@BindingContainer
class AppCoreBindings {

    @SingleIn(AppScope::class)
    @Provides
    fun providePathManager(
        systemDeps: SystemDeps,
    ): PathManager {
        return createPathHelper(systemDeps)
    }

    @SingleIn(AppScope::class)
    @Provides
    fun provideAppDataBase(
        systemDeps: SystemDeps,
        pathManager: PathManager,
    ): AppDataBase {
        return roomDatabaseBuilder<AppDataBase>(
            name = AppDataBase.TABLE_NAME,
            pathManager = pathManager,
            systemDeps = systemDeps,
        )
            .fallbackToDestructiveMigration(true)
            .build()
    }
}
