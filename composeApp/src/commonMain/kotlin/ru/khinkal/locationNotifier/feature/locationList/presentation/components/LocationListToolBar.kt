package ru.khinkal.locationNotifier.feature.locationList.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kmm_location_notifier.composeapp.generated.resources.Res
import kmm_location_notifier.composeapp.generated.resources.app_name
import kmm_location_notifier.composeapp.generated.resources.ic_setting
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.khinkal.locationNotifier.shared.components.topBar.BaseTopBar

@Composable
fun LocationListToolBar(
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BaseTopBar(
        modifier = modifier,
        title = stringResource(Res.string.app_name),
        actions = {
            SettingsItem(onClick = onSettingsClick)
        },
    )
}

@Composable
private fun SettingsItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_setting),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
        )
    }
}
