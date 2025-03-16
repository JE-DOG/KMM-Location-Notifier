package ru.khinkal.locationNotifier.feature.settings.presentation.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.khinkal.locationNotifier.feature.settings.presentation.SettingsScreen

fun NavGraphBuilder.settings(navController: NavController) {
    composable<SettingsScreen>(
        enterTransition = {
            slideInHorizontally { it }
        },
        exitTransition = {
            slideOutHorizontally { -it }
        },
        popExitTransition = {
            slideOutHorizontally { it }
        },
        popEnterTransition = {
            slideInHorizontally { -it }
        },
    ) {
        SettingsScreen(navController)
    }
}
