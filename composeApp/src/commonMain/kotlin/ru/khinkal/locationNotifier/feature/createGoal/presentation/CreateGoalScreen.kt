package ru.khinkal.locationNotifier.feature.createGoal.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ru.khinkal.locationNotifier.feature.createGoal.presentation.content.CreateGoalContent
import ru.khinkal.locationNotifier.feature.createGoal.presentation.vm.CreateGoalViewModel

@Composable
fun CreateGoalScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val viewModel = viewModel {
        CreateGoalViewModel(navController)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    CreateGoalContent(
        modifier = modifier,
        sendAction = viewModel::onAction,
        state = state,
    )
}
