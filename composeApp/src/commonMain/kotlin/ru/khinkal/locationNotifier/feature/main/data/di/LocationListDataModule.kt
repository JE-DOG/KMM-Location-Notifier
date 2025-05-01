package ru.khinkal.locationNotifier.feature.main.data.di

import kmp.core.deps.SystemDeps
import kmp.core.path.PathManager
import kmp.core.storage.room.roomDatabaseBuilder
import ru.khinkal.locationNotifier.feature.main.data.LocationRepositoryImpl
import ru.khinkal.locationNotifier.feature.main.data.storage.LocationStorageDataSource
import ru.khinkal.locationNotifier.feature.main.data.storage.LocationStorageDataSourceImpl
import ru.khinkal.locationNotifier.feature.main.data.storage.room.LocationDao
import ru.khinkal.locationNotifier.feature.main.data.storage.room.LocationDataBase
import ru.khinkal.locationNotifier.feature.main.domain.LocationRepository

class LocationListDataModule(
    private val systemDeps: SystemDeps,
    private val pathManager: PathManager,
) {

    fun provideLocationRepository(): LocationRepository {
        return LocationRepositoryImpl(
            storageDataSource = provideLocationStorageDataSource(),
        )
    }

    private fun provideLocationStorageDataSource(): LocationStorageDataSource {
        return LocationStorageDataSourceImpl(
            locationListDao = provideLocationDao()
        )
    }

    private fun provideLocationDao(): LocationDao {
        return provideLocationDataBase().locationDao()
    }

    private fun provideLocationDataBase(): LocationDataBase {
        return roomDatabaseBuilder<LocationDataBase>(
            name = LocationDataBase.TABLE_NAME,
            pathManager = pathManager,
            systemDeps = systemDeps
        )
            .fallbackToDestructiveMigration(true)
            .build()
    }
}
