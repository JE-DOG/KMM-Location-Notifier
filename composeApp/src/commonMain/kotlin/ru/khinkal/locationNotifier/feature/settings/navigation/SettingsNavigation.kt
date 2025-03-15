package ru.khinkal.locationNotifier.feature.settings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.khinkal.locationNotifier.feature.settings.SettingsScreen

fun NavGraphBuilder.settings(navController: NavController) {
    composable<SettingsScreen> {
        SettingsScreen(navController)
    }
}
