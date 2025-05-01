package ru.khinkal.locationNotifier.feature.settings.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kmp.core.path.PathManager
import ru.khinkal.locationNotifier.core.storage.datastore.DataStoreHelper
import ru.khinkal.locationNotifier.feature.settings.data.SettingsManagerImpl
import ru.khinkal.locationNotifier.feature.settings.data.storage.SettingsLocalDataSource
import ru.khinkal.locationNotifier.feature.settings.domain.SettingsManager

class SettingsDataModule(
    private val pathManager: PathManager,
) {

    private fun provideSettingsDatastore(): DataStore<Preferences> {
        return DataStoreHelper.create(
            name = STORAGE_NAME,
            pathManager = pathManager,
        )
    }

    private fun provideSettingsLocalDataSource(): SettingsLocalDataSource {
        return SettingsLocalDataSource(provideSettingsDatastore())
    }

    fun provideSettingsRepository(): SettingsManager {
        return SettingsManagerImpl(provideSettingsLocalDataSource())
    }

    companion object {

        private const val STORAGE_NAME = "settings"
    }
}
