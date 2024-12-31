package ru.khinkal.locationNotifier.feature.createGoal.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.khinkal.locationNotifier.feature.setGeoPoint.components.SetGeoPointContent

@Composable
fun CreateGoalScreen(
    modifier: Modifier = Modifier,
) {
    SetGeoPointContent(
        modifier = modifier,
    )
}
