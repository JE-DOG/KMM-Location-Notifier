package ru.khinkal.locationNotifier.feature.main.presentation.content.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import ru.khinkal.locationNotifier.feature.goalBroadcaster.model.GoalBroadcastProgress
import ru.khinkal.locationNotifier.shared.theme.AppTheme

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun ActiveGoalProgressContentPreview() {
    AppTheme {
        ActiveGoalProgressContent(
            progress = GoalBroadcastProgress(
                goalName = "Office",
                metersToGoal = 430,
                progress = 0.58f,
            ),
        )
    }
}
