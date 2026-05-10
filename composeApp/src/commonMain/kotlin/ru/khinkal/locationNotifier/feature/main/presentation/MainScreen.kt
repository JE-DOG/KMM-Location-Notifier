package ru.khinkal.locationNotifier.feature.main.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.collectLatest
import ru.khinkal.locationNotifier.feature.main.presentation.content.MainContent
import ru.khinkal.locationNotifier.feature.main.presentation.content.necessssary_permissions.NecessaryPermissionDialog
import ru.khinkal.locationNotifier.feature.main.presentation.vm.MainViewModel
import ru.khinkal.locationNotifier.feature.main.presentation.vm.factory.rememberMainViewModelFactory
import ru.khinkal.locationNotifier.feature.main.presentation.vm.model.MainEvent
import ru.khinkal.locationNotifier.navigation.NavController

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = viewModel(
        factory = rememberMainViewModelFactory(),
    ),
    modifier: Modifier = Modifier,
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.event.collectLatest { event ->
            when (event) {
                is MainEvent.NavigateTo -> navController.navigate(event.route)
            }
        }
    }

    NecessaryPermissionDialog()

    MainContent(
        modifier = modifier,
        state = state,
        sendAction = viewModel::action,
    )
}
