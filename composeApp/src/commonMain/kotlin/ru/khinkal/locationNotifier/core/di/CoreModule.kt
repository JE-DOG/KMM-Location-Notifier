package ru.khinkal.locationNotifier.core.di

import kmp.core.deps.EmptySystemDeps
import kmp.core.deps.SystemDeps
import kmp.core.path.PathManager
import kmp.core.path.createPathHelper
import kmp.core.storage.room.roomDatabaseBuilder
import ru.khinkal.locationNotifier.feature.main.data.storage.room.AppDataBase

class CoreModule {

    fun provideAppDataBase(
        systemDeps: SystemDeps,
        pathManager: PathManager,
    ): AppDataBase {
        return roomDatabaseBuilder<AppDataBase>(
            name = AppDataBase.TABLE_NAME,
            pathManager = pathManager,
            systemDeps = systemDeps
        )
            .fallbackToDestructiveMigration(true)
            .build()
    }

    fun providePathManager(
        systemDeps: SystemDeps = EmptySystemDeps(),
    ): PathManager {
        return createPathHelper(systemDeps)
    }
}
