package ru.khinkal.locationNotifier.feature.settings.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kmp.core.path.PathManager
import ru.khinkal.locationNotifier.core.storage.datastore.DataStoreHelper
import ru.khinkal.locationNotifier.feature.settings.data.SettingsManagerImpl
import ru.khinkal.locationNotifier.feature.settings.data.storage.SettingsLocalDataSource
import ru.khinkal.locationNotifier.feature.settings.domain.SettingsManager

class SettingsDataModule {

    private fun provideSettingsDatastore(
        pathManager: PathManager,
    ): DataStore<Preferences> {
        return DataStoreHelper.create(
            name = STORAGE_NAME,
            pathManager = pathManager,
        )
    }

    private fun provideSettingsLocalDataSource(
        settingsDataStore: DataStore<Preferences>,
    ): SettingsLocalDataSource {
        return SettingsLocalDataSource(settingsDataStore)
    }

    fun provideSettingsManager(
        pathManager: PathManager,
    ): SettingsManager {
        val dataStore = provideSettingsDatastore(pathManager)
        val localDataSource = provideSettingsLocalDataSource(dataStore)
        return SettingsManagerImpl(localDataSource)
    }

    companion object {

        private const val STORAGE_NAME = "settings"
    }
}
