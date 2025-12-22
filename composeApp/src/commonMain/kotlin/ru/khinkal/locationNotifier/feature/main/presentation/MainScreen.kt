package ru.khinkal.locationNotifier.feature.main.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ru.khinkal.locationNotifier.feature.main.presentation.content.MainContent
import ru.khinkal.locationNotifier.feature.main.presentation.content.necessssary_permissions.NecessaryPermissionDialog
import ru.khinkal.locationNotifier.feature.main.presentation.vm.MainViewModel
import ru.khinkal.locationNotifier.feature.main.presentation.vm.factory.rememberMainViewModelFactory

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = viewModel(
        factory = rememberMainViewModelFactory(
            navController = navController,
        ),
    ),
    modifier: Modifier = Modifier,
) {
    val state by viewModel.state.collectAsState()

    NecessaryPermissionDialog()

    MainContent(
        modifier = modifier,
        state = state,
        sendAction = viewModel::action,
    )
}
