package ru.khinkal.locationNotifier.feature.createGoal.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ru.khinkal.locationNotifier.feature.createGoal.presentation.content.CreateGoalContent
import ru.khinkal.locationNotifier.feature.createGoal.presentation.di.CreateGoalComponent
import ru.khinkal.locationNotifier.feature.createGoal.presentation.vm.CreateGoalViewModel
import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint

@Composable
fun CreateGoalScreen(
    navController: NavController,
    goalGeoPoint: GoalGeoPoint?,
    modifier: Modifier = Modifier,
) {
    val viewModel = viewModel {
        val component = CreateGoalComponent.INSTANCE

        CreateGoalViewModel(
            navController = navController,
            initialGoalGeoPoint = goalGeoPoint,
            geoPointRepository = component.goalGeoPointRepository,
        )
    }
    val state by viewModel.state.collectAsStateWithLifecycle()

    CreateGoalContent(
        modifier = modifier,
        sendAction = viewModel::onAction,
        state = state,
    )
}
