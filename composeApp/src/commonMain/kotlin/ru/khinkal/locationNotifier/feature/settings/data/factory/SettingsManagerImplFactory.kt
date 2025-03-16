package ru.khinkal.locationNotifier.feature.settings.data.factory

import kmp.storage.datastore.DataStoreHelper.createDataStore
import ru.khinkal.locationNotifier.feature.settings.data.SettingsManagerImpl
import ru.khinkal.locationNotifier.feature.settings.data.storage.SettingsLocalDataSource

private const val SETTINGS_DATA_STORE_NAME = "settings_datastore"

object SettingsManagerImplFactory {

    fun create(): SettingsManagerImpl {
        val settingsLocalDataSource = SettingsLocalDataSource(
            dataStore = createDataStore(SETTINGS_DATA_STORE_NAME),
        )
        return SettingsManagerImpl(
            settingsLocalDataSource = settingsLocalDataSource,
        )
    }
}
