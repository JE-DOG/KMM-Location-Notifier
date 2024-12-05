package ru.khinkal.locationNotifier.feature.locationList.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.khinkal.locationNotifier.feature.locationList.LocationListScreen
import ru.khinkal.locationNotifier.feature.locationList.vm.LocationListViewModel

fun NavGraphBuilder.locationList(
    navController: NavController,
) {
    composable<LocationListScreen> {
        val viewModel: LocationListViewModel = viewModel {
            LocationListViewModel(navController)
        }

        LocationListScreen(
            viewModel = viewModel,
        )
    }
}