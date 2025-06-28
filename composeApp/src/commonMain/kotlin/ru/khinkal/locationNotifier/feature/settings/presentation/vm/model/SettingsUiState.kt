package ru.khinkal.locationNotifier.feature.settings.presentation.vm.model

data class SettingsUiState(
    val isLoading: Boolean,
    val isNotifyEnabled: Boolean,
) {

    companion object {

        val EMPTY
            get() =
                SettingsUiState(
                    isLoading = false,
                    isNotifyEnabled = false,
                )
    }
}
