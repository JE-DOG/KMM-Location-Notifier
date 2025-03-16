package ru.khinkal.locationNotifier.feature.settings.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ru.khinkal.locationNotifier.feature.settings.presentation.content.SettingsContent
import ru.khinkal.locationNotifier.feature.settings.presentation.vm.SettingsViewModel

@Composable
fun SettingsScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = viewModel(
        factory = remember { SettingsViewModel.createFactory(navController) },
    ),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SettingsContent(
        modifier = modifier,
        state = state,
        sendAction = viewModel::onAction,
    )
}
