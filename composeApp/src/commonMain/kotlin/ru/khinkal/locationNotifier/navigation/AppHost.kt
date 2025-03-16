package ru.khinkal.locationNotifier.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.khinkal.locationNotifier.feature.createGoal.presentation.navigation.createGoal
import ru.khinkal.locationNotifier.feature.locationList.presentation.navigation.LocationListScreen
import ru.khinkal.locationNotifier.feature.locationList.presentation.navigation.locationList
import ru.khinkal.locationNotifier.feature.setGeoPoint.navigation.setGeoPoint
import ru.khinkal.locationNotifier.feature.settings.presentation.navigation.settings

@Composable
fun AppHost(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = LocationListScreen,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None },
    ) {
        locationList(navController)
        settings(navController)
        setGeoPoint(navController)
        createGoal(navController)
    }
}