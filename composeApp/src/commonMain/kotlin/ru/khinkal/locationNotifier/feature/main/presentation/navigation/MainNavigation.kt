package ru.khinkal.locationNotifier.feature.main.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.khinkal.locationNotifier.feature.main.presentation.MainScreen

fun NavGraphBuilder.main(navController: NavController) {
    composable<MainScreen> {
        MainScreen(
            navController = navController,
        )
    }
}
