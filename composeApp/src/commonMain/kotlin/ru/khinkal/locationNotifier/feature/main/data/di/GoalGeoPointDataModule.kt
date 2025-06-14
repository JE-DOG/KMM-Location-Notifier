package ru.khinkal.locationNotifier.feature.main.data.di

import kmp.core.deps.SystemDeps
import kmp.core.path.PathManager
import kmp.core.storage.room.roomDatabaseBuilder
import ru.khinkal.locationNotifier.feature.main.data.GoalGeoPointRepositoryImpl
import ru.khinkal.locationNotifier.feature.main.data.storage.GoalGeoPointStorageDataSource
import ru.khinkal.locationNotifier.feature.main.data.storage.GoalGeoPointStorageDataSourceImpl
import ru.khinkal.locationNotifier.feature.main.data.storage.room.GoalGeoPointDao
import ru.khinkal.locationNotifier.feature.main.data.storage.room.GoalGeoPointDaoDataBase
import ru.khinkal.locationNotifier.feature.main.domain.GoalGeoPointRepository

class GoalGeoPointDataModule(
    private val systemDeps: SystemDeps,
    private val pathManager: PathManager,
) {

    fun provideGoalGeoPointRepository(): GoalGeoPointRepository {
        return GoalGeoPointRepositoryImpl(
            storageDataSource = provideLocationStorageDataSource(),
        )
    }

    private fun provideLocationStorageDataSource(): GoalGeoPointStorageDataSource {
        return GoalGeoPointStorageDataSourceImpl(
            locationListDao = provideGoalGeoPointDaoDao()
        )
    }

    private fun provideGoalGeoPointDaoDao(): GoalGeoPointDao {
        return provideGoalGeoPointDaoDataBase().goalGeoPointDao()
    }

    private fun provideGoalGeoPointDaoDataBase(): GoalGeoPointDaoDataBase {
        return roomDatabaseBuilder<GoalGeoPointDaoDataBase>(
            name = GoalGeoPointDaoDataBase.TABLE_NAME,
            pathManager = pathManager,
            systemDeps = systemDeps
        )
            .fallbackToDestructiveMigration(true)
            .build()
    }
}
