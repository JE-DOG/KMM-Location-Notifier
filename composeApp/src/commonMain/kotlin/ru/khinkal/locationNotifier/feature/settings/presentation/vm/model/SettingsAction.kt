package ru.khinkal.locationNotifier.feature.settings.presentation.vm.model

interface SettingsAction {

    data object OnBackClicked : SettingsAction

    // Set values
    data class SetVibrationEnabled(val vibrationEnabled: Boolean) : SettingsAction
    data class SetLocationUpdateSeconds(
        val locationUpdateSecondsInterval: Int,
    ) : SettingsAction
}