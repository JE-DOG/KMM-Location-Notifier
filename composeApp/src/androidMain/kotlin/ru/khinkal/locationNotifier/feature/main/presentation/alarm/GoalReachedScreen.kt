package ru.khinkal.locationNotifier.feature.main.presentation.alarm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.khinkal.locationNotifier.R
import ru.khinkal.locationNotifier.shared.theme.AppTheme

@Composable
fun GoalReachedScreen(
    goalName: String,
    onCancelClick: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .statusBarsPadding()
                .navigationBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Spacer(Modifier.weight(0.2f))

            Text(
                text = stringResource(R.string.goal_reached_screen_title),
                style = MaterialTheme.typography.displayLarge,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = goalName,
                style = MaterialTheme.typography.headlineMedium,
            )

            Spacer(Modifier.weight(1f))

            LargeFloatingActionButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onCancelClick,
            ) {
                Text(
                    text = stringResource(R.string.goal_reached_close_button),
                    style = MaterialTheme.typography.titleLarge,
                )
            }

            Spacer(Modifier.height(32.dp))
        }
    }
}

// region Preview
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun GoalReachedScreenPreview() {
    AppTheme {
        GoalReachedScreen(
            goalName = "Store",
            onCancelClick = {},
        )
    }
}
// endregion Preview
