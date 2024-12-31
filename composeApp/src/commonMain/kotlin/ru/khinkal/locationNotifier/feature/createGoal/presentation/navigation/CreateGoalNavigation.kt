package ru.khinkal.locationNotifier.feature.createGoal.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.khinkal.locationNotifier.feature.createGoal.presentation.CreateGoalScreen

fun NavGraphBuilder.createGoal(navController: NavController) {
    composable<CreateGoalScreen> {
        CreateGoalScreen(navController)
    }
}