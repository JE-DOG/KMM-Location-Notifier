package ru.khinkal.locationNotifier.feature.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ru.khinkal.locationNotifier.feature.settings.components.topbar.SettingsTopBar
import ru.khinkal.locationNotifier.feature.settings.content.SettingsMainContent
import ru.khinkal.locationNotifier.feature.settings.vm.SettingsViewModel

@Composable
fun SettingsScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = viewModel { SettingsViewModel(navController) },
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            SettingsTopBar(
                modifier = Modifier.fillMaxWidth(),
                sendAction = viewModel::onAction,
            )
        },
    ) { paddingValues ->
        SettingsMainContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            state = state,
            sendAction = viewModel::onAction,
        )
    }
}
