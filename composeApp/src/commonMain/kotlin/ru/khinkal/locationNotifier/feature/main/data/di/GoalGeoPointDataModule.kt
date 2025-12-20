package ru.khinkal.locationNotifier.feature.main.data.di

import ru.khinkal.locationNotifier.feature.main.data.GoalGeoPointRepositoryImpl
import ru.khinkal.locationNotifier.feature.main.data.storage.GoalGeoPointStorageDataSource
import ru.khinkal.locationNotifier.feature.main.data.storage.GoalGeoPointStorageDataSourceImpl
import ru.khinkal.locationNotifier.feature.main.data.storage.room.AppDataBase
import ru.khinkal.locationNotifier.feature.main.data.storage.room.GoalGeoPointDao
import ru.khinkal.locationNotifier.feature.main.domain.GoalGeoPointRepository

class GoalGeoPointDataModule {

    fun provideGoalGeoPointRepository(appDataBase: AppDataBase): GoalGeoPointRepository {
        return GoalGeoPointRepositoryImpl(
            storageDataSource = provideLocationStorageDataSource(appDataBase),
        )
    }

    private fun provideLocationStorageDataSource(
        appDataBase: AppDataBase,
    ): GoalGeoPointStorageDataSource {
        return GoalGeoPointStorageDataSourceImpl(
            locationListDao = provideGoalGeoPointDaoDao(appDataBase)
        )
    }

    private fun provideGoalGeoPointDaoDao(appDataBase: AppDataBase): GoalGeoPointDao {
        return appDataBase.goalGeoPointDao()
    }
}
