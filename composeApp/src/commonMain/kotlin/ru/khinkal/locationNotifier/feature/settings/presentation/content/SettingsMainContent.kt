package ru.khinkal.locationNotifier.feature.settings.presentation.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kmm_location_notifier.composeapp.generated.resources.Res
import kmm_location_notifier.composeapp.generated.resources.settings_notify_title
import org.jetbrains.compose.resources.stringResource
import ru.khinkal.locationNotifier.feature.settings.presentation.components.SwitchItem
import ru.khinkal.locationNotifier.feature.settings.presentation.vm.model.SettingsAction
import ru.khinkal.locationNotifier.feature.settings.presentation.vm.model.SettingsUiState

@Composable
fun SettingsMainContent(
    state: SettingsUiState,
    sendAction: (SettingsAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(
                horizontal = 16.dp,
                vertical = 20.dp,
            ),
    ) {
        Notify(
            isNotifyEnabled = state.isNotifyEnabled,
            onValueChange = { isNotifyEnabled ->
                val action = SettingsAction.SetNotifyEnabled(isNotifyEnabled)
                sendAction(action)
            },
        )
    }
}

@Composable
private fun Notify(
    isNotifyEnabled: Boolean,
    onValueChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    SwitchItem(
        modifier = modifier,
        title = stringResource(Res.string.settings_notify_title),
        checked = isNotifyEnabled,
        onValueChange = onValueChange,
    )
}
