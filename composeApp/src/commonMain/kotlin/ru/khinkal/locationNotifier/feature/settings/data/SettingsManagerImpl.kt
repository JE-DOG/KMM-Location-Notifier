package ru.khinkal.locationNotifier.feature.settings.data

import kotlinx.coroutines.flow.Flow
import ru.khinkal.locationNotifier.feature.settings.data.storage.SettingsLocalDataSource
import ru.khinkal.locationNotifier.feature.settings.domain.SettingsManager

class SettingsManagerImpl(
    private val settingsLocalDataSource: SettingsLocalDataSource,
): SettingsManager {

    override suspend fun isVibrationEnabled(): Boolean =
        settingsLocalDataSource.isVibrationEnabled()

    override suspend fun setIsVibrationEnabled(enable: Boolean) =
        settingsLocalDataSource.setIsVibrationEnabled(enable)

    override fun getLocationUpdateSeconds(): Flow<Int> =
        settingsLocalDataSource.getLocationUpdateSeconds()

    override suspend fun setLocationUpdateSeconds(seconds: Int) =
        settingsLocalDataSource.setLocationUpdateSeconds(seconds)
}
