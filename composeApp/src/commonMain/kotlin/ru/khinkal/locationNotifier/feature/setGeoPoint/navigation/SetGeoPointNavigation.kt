package ru.khinkal.locationNotifier.feature.setGeoPoint.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.khinkal.locationNotifier.feature.setGeoPoint.SetGeoPointScreen

fun NavGraphBuilder.setGeoPoint(
    navController: NavController,
) {
    composable<SetGeoPointScreen>(
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
        SetGeoPointScreen(navController)
    }
}
