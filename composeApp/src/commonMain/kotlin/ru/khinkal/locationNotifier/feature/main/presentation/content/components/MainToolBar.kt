package ru.khinkal.locationNotifier.feature.main.presentation.content.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kmm_location_notifier.composeapp.generated.resources.Res
import kmm_location_notifier.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource
import ru.khinkal.locationNotifier.shared.components.topBar.BaseTopBar

@Composable
fun LocationListToolBar(
    modifier: Modifier = Modifier,
) {
    BaseTopBar(
        modifier = modifier,
        title = stringResource(Res.string.app_name),
    )
}
