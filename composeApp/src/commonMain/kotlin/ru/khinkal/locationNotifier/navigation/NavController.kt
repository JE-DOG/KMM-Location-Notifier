package ru.khinkal.locationNotifier.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.serialization.NavBackStackSerializer
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.PolymorphicSerializer

class NavController internal constructor(
    val backStack: NavBackStack<NavRoute>,
) {

    fun navigate(route: NavRoute) {
        backStack.add(route)
    }

    fun popBackStack() {
        if (backStack.size > 1) {
            backStack.removeAt(backStack.lastIndex)
        }
    }
}

@Composable
fun rememberNavController(
    configuration: SavedStateConfiguration,
    vararg routes: NavRoute,
): NavController {
    val backStack = rememberSerializable(
        configuration = configuration,
        serializer = NavBackStackSerializer(PolymorphicSerializer(NavRoute::class)),
    ) {
        NavBackStack(*routes)
    }

    return remember(backStack) {
        NavController(backStack)
    }
}
