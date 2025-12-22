package ru.khinkal.locationNotifier.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.khinkal.locationNotifier.feature.createGoal.presentation.navigation.createGoal
import ru.khinkal.locationNotifier.feature.main.presentation.navigation.MainScreen
import ru.khinkal.locationNotifier.feature.main.presentation.navigation.main
import ru.khinkal.locationNotifier.feature.setGeoPoint.navigation.setGeoPoint

@Composable
fun AppHost(
    modifier: Modifier = Modifier,
) {
    val navController: NavHostController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = MainScreen,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None },
    ) {
        main(navController)
        setGeoPoint(navController)
        createGoal(navController)
    }
}
