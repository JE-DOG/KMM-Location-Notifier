package ru.khinkal.locationNotifier.feature.createGoal.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import ru.khinkal.locationNotifier.core.navigation.serializableNavType
import ru.khinkal.locationNotifier.feature.createGoal.presentation.CreateGoalScreen
import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint
import kotlin.reflect.typeOf

fun NavGraphBuilder.createGoal(navController: NavController) {
    composable<CreateGoalScreen>(
        typeMap = mapOf(
            typeOf<GoalGeoPoint?>() to
                    serializableNavType<GoalGeoPoint?>(true),
        ),
        enterTransition = {
            slideInVertically { it }
        },
        popExitTransition = {
            slideOutVertically { it }
        },
        popEnterTransition = { EnterTransition.None },
    ) { backEntry ->
        val route = backEntry.toRoute<CreateGoalScreen>()

        CreateGoalScreen(
            navController = navController,
            goalGeoPoint = route.geoPoint,
        )
    }
}
