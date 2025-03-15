package ru.khinkal.locationNotifier.feature.settings.vm.model

data class SettingsUiState(
    val vibrationEnabled: Boolean,
    val locationUpdateSecondsInterval: Int,
) {

    companion object {

        val EMPTY get() =
            SettingsUiState(
                vibrationEnabled = false,
                locationUpdateSecondsInterval = 0,
            )
    }
}
