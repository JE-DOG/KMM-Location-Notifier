package ru.khinkal.locationNotifier.feature.settings.presentation.vm.model

interface SettingsAction {

    data object OnBackClicked : SettingsAction

    data class SetNotifyEnabled(val notifyEnabled: Boolean) : SettingsAction
}