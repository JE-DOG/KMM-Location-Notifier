package ru.khinkal.locationNotifier.feature.settings.data.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first

class SettingsLocalDataSource(
    private val dataStore: DataStore<Preferences>,
) {

    suspend fun isNotifyEnabled(): Boolean {
        val preferences = dataStore.data.first()
        return preferences[NOTIFY_ENABLED_KEY] ?: false
    }

    suspend fun setIsNotifyEnabled(enable: Boolean) {
        dataStore.edit { preferences ->
            preferences[NOTIFY_ENABLED_KEY] = enable
        }
    }

    companion object {

        private val NOTIFY_ENABLED_KEY
            get() = booleanPreferencesKey("notify_enabled_key")
    }
}
