package ru.khinkal.locationNotifier.feature.settings.presentation.components.topbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kmm_location_notifier.composeapp.generated.resources.Res
import kmm_location_notifier.composeapp.generated.resources.settings_topbar_title
import org.jetbrains.compose.resources.stringResource
import ru.khinkal.locationNotifier.feature.settings.presentation.vm.model.SettingsAction
import ru.khinkal.locationNotifier.shared.components.topBar.BaseTopBar

@Composable
fun SettingsTopBar(
    modifier: Modifier = Modifier,
    sendAction: (SettingsAction) -> Unit,
) {
    BaseTopBar(
        modifier = modifier,
        title = stringResource(Res.string.settings_topbar_title),
        onBackClick = {
            val action = SettingsAction.OnBackClicked
            sendAction(action)
        },
    )
}
