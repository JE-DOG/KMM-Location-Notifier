package ru.khinkal.locationNotifier.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import ru.khinkal.locationNotifier.feature.createGoal.presentation.CreateGoalScreen
import ru.khinkal.locationNotifier.feature.createGoal.presentation.navigation.CreateGoalRoute
import ru.khinkal.locationNotifier.feature.main.presentation.MainScreen
import ru.khinkal.locationNotifier.feature.main.presentation.navigation.MainRoute
import ru.khinkal.locationNotifier.feature.setGeoPoint.SetGeoPointScreen
import ru.khinkal.locationNotifier.feature.setGeoPoint.navigation.SetGeoPointRoute
import ru.khinkal.locationNotifier.shared.navigation.rememberNavResultStore

private val appNavConfiguration = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavRoute::class) {
            subclass(MainRoute::class)
            subclass(CreateGoalRoute::class)
            subclass(SetGeoPointRoute::class)
        }
    }
}

@Composable
fun AppHost(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController(
        configuration = appNavConfiguration,
        MainRoute,
    )
    val navResultStore = rememberNavResultStore()

    NavDisplay(
        modifier = modifier,
        backStack = navController.backStack,
        onBack = navController::popBackStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        transitionSpec = { EnterTransition.None togetherWith ExitTransition.None },
        popTransitionSpec = { EnterTransition.None togetherWith ExitTransition.None },
        predictivePopTransitionSpec = { _ ->
            EnterTransition.None togetherWith ExitTransition.None
        },
        entryProvider = entryProvider {
            entry<MainRoute> {
                MainScreen(navController = navController)
            }

            entry<CreateGoalRoute>(
                metadata = NavDisplay.transitionSpec {
                    slideInVertically { it } togetherWith ExitTransition.None
                } + NavDisplay.popTransitionSpec {
                    EnterTransition.None togetherWith slideOutVertically { it }
                },
            ) { route ->
                CreateGoalScreen(
                    navController = navController,
                    navResultStore = navResultStore,
                    goalGeoPoint = route.geoPoint,
                )
            }

            entry<SetGeoPointRoute>(
                metadata = NavDisplay.transitionSpec {
                    slideInHorizontally { it } togetherWith ExitTransition.None
                } + NavDisplay.popTransitionSpec {
                    EnterTransition.None togetherWith slideOutHorizontally { it }
                },
            ) {
                SetGeoPointScreen(
                    navController = navController,
                    navResultStore = navResultStore,
                )
            }
        },
    )
}
