package ru.khinkal.locationNotifier

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import notification.getNotificationService
import ru.khinkal.locationNotifier.navigation.AppHost
import ru.khinkal.locationNotifier.shared.theme.AppTheme

@Composable
internal fun App() {
    AppTheme {
        LaunchedEffect(Unit) {
            val notificationService = getNotificationService()
            notificationService.sendNotification(
                0,
                title = "Hello, world!",
                description = "First multiplatform notification)",
                isSoundEnabled = true,
            )
        }
        AppHost(
            modifier = Modifier
                .fillMaxSize(),
        )
    }
}
