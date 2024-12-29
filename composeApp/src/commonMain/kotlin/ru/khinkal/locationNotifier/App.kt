package ru.khinkal.locationNotifier

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import location.getLocationService
import ru.khinkal.locationNotifier.navigation.AppHost
import ru.khinkal.locationNotifier.shared.theme.AppTheme

@Composable
internal fun App() {
    AppTheme {
        LaunchedEffect(Unit) {
            getLocationService().startBroadcast(10.0, {

            })
        }
        AppHost(
            modifier = Modifier
                .fillMaxSize(),
        )
    }
}
