package ru.khinkal.locationNotifier.feature.settings.data.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.first

class SettingsLocalDataSource(
    private val dataStore: DataStore<Preferences>,
) {

    suspend fun isVibrationEnabled(): Boolean {
        val preferences = dataStore.data.first()
        return preferences[VIBRATION_ENABLED_KEY] ?: false
    }

    suspend fun setIsVibrationEnabled(enable: Boolean) {
        dataStore.edit {  preferences ->
            preferences[VIBRATION_ENABLED_KEY] = enable
        }
    }

    suspend fun getLocationUpdateSeconds(): Int {
        val preferences = dataStore.data.first()
        return preferences[LOCATION_UPDATE_SECONDS_KEY] ?: 15
    }

    suspend fun setLocationUpdateSeconds(seconds: Int) {
        dataStore.edit {  preferences ->
            preferences[LOCATION_UPDATE_SECONDS_KEY] = seconds
        }
    }

    companion object {

        private val VIBRATION_ENABLED_KEY
            get() = booleanPreferencesKey("vibration_enabled_key")
        private val LOCATION_UPDATE_SECONDS_KEY
            get() = intPreferencesKey("location_update_seconds_key")
    }
}
