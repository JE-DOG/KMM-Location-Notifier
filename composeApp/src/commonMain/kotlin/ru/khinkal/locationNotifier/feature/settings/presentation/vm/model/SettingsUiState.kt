package ru.khinkal.locationNotifier.feature.settings.presentation.vm.model

data class SettingsUiState(
    val isLoading: Boolean,
    val isVibrationEnabled: Boolean,
    val locationUpdateSeconds: Int,
) {

    companion object {

        val EMPTY get() =
            SettingsUiState(
                isLoading = false,
                isVibrationEnabled = false,
                locationUpdateSeconds = 0,
            )
    }
}
