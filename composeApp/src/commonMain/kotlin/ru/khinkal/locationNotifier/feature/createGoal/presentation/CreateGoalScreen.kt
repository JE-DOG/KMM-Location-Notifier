package ru.khinkal.locationNotifier.feature.createGoal.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.collectLatest
import ru.khinkal.locationNotifier.feature.createGoal.presentation.content.CreateGoalContent
import ru.khinkal.locationNotifier.feature.createGoal.presentation.di.createCreateGoalGraph
import ru.khinkal.locationNotifier.feature.createGoal.presentation.vm.CreateGoalViewModel
import ru.khinkal.locationNotifier.feature.createGoal.presentation.vm.model.CreateGoalEvent
import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint
import ru.khinkal.locationNotifier.navigation.NavController
import ru.khinkal.locationNotifier.shared.navigation.NavResultStore
import ru.khinkal.locationNotifier.shared.navigation.ResultKeys

@Composable
fun CreateGoalScreen(
    navController: NavController,
    navResultStore: NavResultStore,
    goalGeoPoint: GoalGeoPoint?,
    modifier: Modifier = Modifier,
) {
    val viewModel = viewModel {
        val graph = createCreateGoalGraph()

        CreateGoalViewModel(
            initialGoalGeoPoint = goalGeoPoint,
            geoPointRepository = graph.goalGeoPointRepository,
        )
    }
    val state by viewModel.state.collectAsStateWithLifecycle()

    navResultStore
        .getResult<String>(ResultKeys.GEO_POINT_SELECTED)
        .value
        ?.let { result ->
            viewModel.onGeoPointSelectedJson(result)
        }

    LaunchedEffect(viewModel) {
        viewModel.event.collectLatest { event ->
            when (event) {
                CreateGoalEvent.NavigateBack -> navController.popBackStack()
                is CreateGoalEvent.NavigateTo -> navController.navigate(event.route)
            }
        }
    }

    CreateGoalContent(
        modifier = modifier,
        sendAction = viewModel::onAction,
        state = state,
    )
}
