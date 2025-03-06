package ru.khinkal.locationNotifier.feature.createGoal.presentation.content.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kmm_location_notifier.composeapp.generated.resources.Res
import kmm_location_notifier.composeapp.generated.resources.create_goal_screen_title
import org.jetbrains.compose.resources.stringResource
import ru.khinkal.locationNotifier.shared.components.topBar.BaseTopBar

@Composable
fun CreateGoalTopBar(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
) {
    BaseTopBar(
        modifier = modifier,
        title = stringResource(Res.string.create_goal_screen_title),
        onBackClicked = onBackClicked,
    )
}