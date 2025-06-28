package ru.khinkal.locationNotifier.feature.settings.data

import ru.khinkal.locationNotifier.feature.settings.data.storage.SettingsLocalDataSource
import ru.khinkal.locationNotifier.feature.settings.domain.SettingsManager

class SettingsManagerImpl(
    private val settingsLocalDataSource: SettingsLocalDataSource,
) : SettingsManager {

    override suspend fun isNotifyEnabled(): Boolean =
        settingsLocalDataSource.isNotifyEnabled()

    override suspend fun setIsNotifyEnabled(enable: Boolean) =
        settingsLocalDataSource.setIsNotifyEnabled(enable)
}
