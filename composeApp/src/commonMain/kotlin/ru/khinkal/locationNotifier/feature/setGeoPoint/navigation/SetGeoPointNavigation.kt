package ru.khinkal.locationNotifier.feature.setGeoPoint.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.khinkal.locationNotifier.feature.setGeoPoint.SetGeoPointScreen

fun NavGraphBuilder.setGeoPoint(
    navController: NavController,
) {
    composable<SetGeoPointScreen> {
        SetGeoPointScreen(navController)
    }
}
