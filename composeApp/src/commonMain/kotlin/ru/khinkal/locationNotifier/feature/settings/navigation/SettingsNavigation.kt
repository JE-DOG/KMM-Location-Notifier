package ru.khinkal.locationNotifier.feature.settings.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.khinkal.locationNotifier.feature.settings.SettingsScreen

fun NavGraphBuilder.settings() {
    composable<SettingsScreen> {
        SettingsScreen()
    }
}