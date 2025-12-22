package ru.khinkal.locationNotifier.feature.main.presentation.content.necessssary_permissions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mohamedrejeb.calf.permissions.ExperimentalPermissionsApi
import com.mohamedrejeb.calf.permissions.Permission
import com.mohamedrejeb.calf.permissions.isGranted
import com.mohamedrejeb.calf.permissions.isNotGranted
import com.mohamedrejeb.calf.permissions.rememberPermissionState
import kmm_location_notifier.composeapp.generated.resources.Res
import kmm_location_notifier.composeapp.generated.resources.ic_my_location
import kmm_location_notifier.composeapp.generated.resources.ic_notification
import kmm_location_notifier.composeapp.generated.resources.main_screen_necessary_permissions_location
import kmm_location_notifier.composeapp.generated.resources.main_screen_necessary_permissions_notification
import kmm_location_notifier.composeapp.generated.resources.main_screen_necessary_permissions_title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NecessaryPermissionDialog() {
    val locationPermission = rememberPermissionState(permission = Permission.FineLocation)
    val notificationsPermission = rememberPermissionState(permission = Permission.Notification)

    if (locationPermission.status.isNotGranted || locationPermission.status.isNotGranted) {
        Dialog(
            onDismissRequest = {},
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false,
            ),
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = MaterialTheme.shapes.large,
            ) {
                Content(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    locationGranted = locationPermission.status.isGranted,
                    notificationGranted = notificationsPermission.status.isGranted,
                    onLocationClick = { locationPermission.openAppSettings() },
                    onNotificationClick = { notificationsPermission.openAppSettings() },
                )
            }
        }
    }
}

@Composable
private fun Content(
    locationGranted: Boolean,
    notificationGranted: Boolean,
    onLocationClick: () -> Unit,
    onNotificationClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Title(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        )

        Permissions(
            modifier = Modifier.fillMaxWidth(),
            locationGranted = locationGranted,
            notificationGranted = notificationGranted,
            onLocationClick = onLocationClick,
            onNotificationClick = onNotificationClick,
        )
    }
}

@Composable
private fun Title(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = stringResource(Res.string.main_screen_necessary_permissions_title),
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun Permissions(
    locationGranted: Boolean,
    notificationGranted: Boolean,
    onLocationClick: () -> Unit,
    onNotificationClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        PermissionItemCard(
            modifier = Modifier
                .fillMaxWidth(),
            icon = vectorResource(Res.drawable.ic_my_location),
            name = stringResource(Res.string.main_screen_necessary_permissions_location),
            granted = locationGranted,
            onClick = onLocationClick,
            contentPadding = PaddingValues(
                vertical = 12.dp,
                horizontal = 16.dp,
            ),
        )

        HorizontalDivider(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )

        PermissionItemCard(
            modifier = Modifier
                .fillMaxWidth(),
            icon = vectorResource(Res.drawable.ic_notification),
            name = stringResource(Res.string.main_screen_necessary_permissions_notification),
            granted = notificationGranted,
            onClick = onNotificationClick,
            contentPadding = PaddingValues(
                vertical = 12.dp,
                horizontal = 16.dp,
            ),
        )
    }
}

@Composable
private fun PermissionItemCard(
    icon: ImageVector,
    name: String,
    granted: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
) {
    Surface(
        modifier = modifier,
        onClick = onClick,
        color = Color.Transparent,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier
                    .size(24.dp),
                imageVector = icon,
                contentDescription = null,
            )

            Text(
                modifier = Modifier.weight(1f),
                text = name,
                style = MaterialTheme.typography.bodyLarge,
            )

            Checkbox(
                checked = granted,
                onCheckedChange = null,
            )
        }
    }
}
