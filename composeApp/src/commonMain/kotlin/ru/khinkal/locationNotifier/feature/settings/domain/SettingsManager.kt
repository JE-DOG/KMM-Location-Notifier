package ru.khinkal.locationNotifier.feature.settings.domain

interface SettingsManager {

    suspend fun isVibrationEnabled(): Boolean

    suspend fun setIsVibrationEnabled(enable: Boolean)

    suspend fun getLocationUpdateSeconds(): Int

    suspend fun setLocationUpdateSeconds(seconds: Int)
}
