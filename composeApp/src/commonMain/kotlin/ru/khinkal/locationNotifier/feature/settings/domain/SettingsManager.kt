package ru.khinkal.locationNotifier.feature.settings.domain

import kotlinx.coroutines.flow.Flow

interface SettingsManager {

    suspend fun isVibrationEnabled(): Boolean

    suspend fun setIsVibrationEnabled(enable: Boolean)

    fun getLocationUpdateSeconds(): Flow<Int>

    suspend fun setLocationUpdateSeconds(seconds: Int)
}
