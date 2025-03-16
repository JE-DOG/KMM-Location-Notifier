package ru.khinkal.locationNotifier.feature.createGoal.presentation.content

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.khinkal.locationNotifier.feature.createGoal.presentation.content.components.CreateGoalFloatingButton
import ru.khinkal.locationNotifier.feature.createGoal.presentation.content.components.CreateGoalTopBar
import ru.khinkal.locationNotifier.feature.createGoal.presentation.content.main.CreateGoalMainContent
import ru.khinkal.locationNotifier.feature.createGoal.presentation.vm.model.CreateGoalAction
import ru.khinkal.locationNotifier.feature.createGoal.presentation.vm.model.CreateGoalState

@Composable
fun CreateGoalContent(
    state: CreateGoalState,
    sendAction: (CreateGoalAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets
            .add(WindowInsets.ime),
        topBar = {
            CreateGoalTopBar(
                onBackClick = {
                    val action = CreateGoalAction.GoBack
                    sendAction(action)
                },
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            CreateGoalFloatingButton(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                onCreateGoalClick = {
                    val action = CreateGoalAction.StartBroadcast
                    sendAction(action)
                },
            )
        },
    ) { paddingValues ->
        CreateGoalMainContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            state = state,
            sendAction = sendAction,
        )
    }
}