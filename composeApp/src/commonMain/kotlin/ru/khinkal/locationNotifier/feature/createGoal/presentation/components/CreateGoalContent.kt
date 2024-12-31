package ru.khinkal.locationNotifier.feature.createGoal.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.khinkal.locationNotifier.feature.createGoal.presentation.components.main.CreateGoalMainContent

@Composable
fun CreateGoalContent(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
    ) { paddingValues ->
        CreateGoalMainContent(
            Modifier
                .fillMaxSize()
                .padding(paddingValues),
        )
    }
}