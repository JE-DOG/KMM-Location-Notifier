package ru.khinkal.locationNotifier.feature.createGoal.presentation.di.bindings

import dev.zacsweers.metro.BindingContainer
import dev.zacsweers.metro.Provides
import ru.khinkal.locationNotifier.feature.createGoal.presentation.di.deps.CreateGoalDeps
import ru.khinkal.locationNotifier.feature.main.data.GoalGeoPointRepositoryImpl
import ru.khinkal.locationNotifier.feature.main.data.storage.GoalGeoPointStorageDataSource
import ru.khinkal.locationNotifier.feature.main.data.storage.GoalGeoPointStorageDataSourceImpl
import ru.khinkal.locationNotifier.feature.main.data.storage.room.AppDataBase
import ru.khinkal.locationNotifier.feature.main.data.storage.room.GoalGeoPointDao
import ru.khinkal.locationNotifier.feature.main.domain.GoalGeoPointRepository

@BindingContainer
class CreateGoalDataBindings {

    @Provides
    fun provideGoalGeoPointRepository(
        deps: CreateGoalDeps,
    ): GoalGeoPointRepository {
        return GoalGeoPointRepositoryImpl(
            storageDataSource = provideGoalGeoPointStorageDataSource(deps.appDataBase),
        )
    }

    @Provides
    fun provideGoalGeoPointStorageDataSource(
        appDataBase: AppDataBase,
    ): GoalGeoPointStorageDataSource {
        return GoalGeoPointStorageDataSourceImpl(
            locationListDao = provideGoalGeoPointDao(appDataBase),
        )
    }

    @Provides
    fun provideGoalGeoPointDao(
        appDataBase: AppDataBase,
    ): GoalGeoPointDao {
        return appDataBase.goalGeoPointDao()
    }
}
