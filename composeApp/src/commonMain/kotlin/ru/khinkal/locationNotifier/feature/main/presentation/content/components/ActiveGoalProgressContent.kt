package ru.khinkal.locationNotifier.feature.main.presentation.content.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kmm_location_notifier.composeapp.generated.resources.Res
import kmm_location_notifier.composeapp.generated.resources.main_screen_goal_progress_distance
import kmm_location_notifier.composeapp.generated.resources.main_screen_goal_progress_title
import org.jetbrains.compose.resources.stringResource
import ru.khinkal.locationNotifier.feature.goalBroadcaster.model.GoalBroadcastProgress

@Composable
fun ActiveGoalProgressContent(
    progress: GoalBroadcastProgress,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.onPrimary,
            )
            .padding(contentPadding),
    ) {
        Text(
            text = stringResource(Res.string.main_screen_goal_progress_title, progress.goalName),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
        )

        Spacer(modifier = Modifier.height(8.dp))

        LinearProgressIndicator(
            progress = { progress.progress.coerceIn(0f, 1f) },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(
                Res.string.main_screen_goal_progress_distance,
                progress.metersToGoal,
            ),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}
