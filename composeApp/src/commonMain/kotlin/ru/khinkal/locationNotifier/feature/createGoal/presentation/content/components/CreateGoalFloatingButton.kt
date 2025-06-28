package ru.khinkal.locationNotifier.feature.createGoal.presentation.content.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kmm_location_notifier.composeapp.generated.resources.Res
import kmm_location_notifier.composeapp.generated.resources.create_goal_create
import org.jetbrains.compose.resources.stringResource
import ru.khinkal.locationNotifier.shared.theme.AppTypography

@Composable
fun CreateGoalFloatingButton(
    enabled: Boolean,
    onCreateGoalClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        modifier = modifier
            .fillMaxWidth(),
        enabled = enabled,
        onClick = onCreateGoalClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.primary,
        ),
    ) {
        Text(
            text = stringResource(Res.string.create_goal_create),
            style = AppTypography.Medium_14_500,
        )
    }
}
