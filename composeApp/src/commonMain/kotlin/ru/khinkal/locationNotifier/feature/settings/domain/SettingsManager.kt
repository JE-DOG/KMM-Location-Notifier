package ru.khinkal.locationNotifier.feature.settings.domain

interface SettingsManager {

    suspend fun isNotifyEnabled(): Boolean

    suspend fun setIsNotifyEnabled(enable: Boolean)
}
