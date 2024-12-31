package ru.khinkal.locationNotifier.feature.createGoal.presentation.content

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        topBar = {
            CreateGoalTopBar(
                onBackClicked = {
                    val action = CreateGoalAction.GoBack
                    sendAction(action)
                },
            )
        }
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