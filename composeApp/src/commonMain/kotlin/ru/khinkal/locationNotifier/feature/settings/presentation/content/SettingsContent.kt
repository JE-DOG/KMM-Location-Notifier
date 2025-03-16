package ru.khinkal.locationNotifier.feature.settings.presentation.content

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.khinkal.locationNotifier.feature.settings.presentation.components.topbar.SettingsTopBar
import ru.khinkal.locationNotifier.feature.settings.presentation.vm.model.SettingsAction
import ru.khinkal.locationNotifier.feature.settings.presentation.vm.model.SettingsUiState

@Composable
fun SettingsContent(
    state: SettingsUiState,
    sendAction: (SettingsAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            SettingsTopBar(
                modifier = Modifier.fillMaxWidth(),
                sendAction = sendAction,
            )
        },
    ) { paddingValues ->
        when {
            state.isLoading -> SettingsLoadingContent(
                modifier = Modifier.fillMaxSize(),
            )

            else -> SettingsMainContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                state = state,
                sendAction = sendAction,
            )
        }
    }
}
