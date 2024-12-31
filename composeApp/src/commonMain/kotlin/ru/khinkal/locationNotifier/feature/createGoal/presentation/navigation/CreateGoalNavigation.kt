package ru.khinkal.locationNotifier.feature.createGoal.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.khinkal.locationNotifier.feature.createGoal.presentation.CreateGoalScreen

fun NavGraphBuilder.createGoal() {
    composable<CreateGoalScreen> {
        CreateGoalScreen()
    }
}