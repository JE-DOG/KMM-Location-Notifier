package ru.khinkal.locationNotifier.feature.locationList.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.khinkal.locationNotifier.feature.locationList.LocationListScreen

fun NavGraphBuilder.locationList(
    navController: NavController,
) {
    composable<LocationListScreen> {
        LocationListScreen(
            navController = navController,
        )
    }
}