package ru.khinkal.locationNotifier.feature.createGoal.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.khinkal.locationNotifier.feature.createGoal.presentation.CreateGoalScreen

fun NavGraphBuilder.createGoal(navController: NavController) {
    composable<CreateGoalScreen>(
        enterTransition = {
            slideInVertically { it }
        },
        popExitTransition = {
            slideOutVertically { it }
        },
        popEnterTransition = { EnterTransition.None },
    ) {
        CreateGoalScreen(navController)
    }
}
