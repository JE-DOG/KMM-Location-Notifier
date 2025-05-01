package ru.khinkal.locationNotifier.feature.main.presentation.vm.factory

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavController
import ru.khinkal.locationNotifier.feature.main.presentation.di.MainComponent
import ru.khinkal.locationNotifier.feature.main.presentation.vm.MainViewModel

@Composable
fun rememberMainViewModelFactory(
    navController: NavController,
): ViewModelProvider.Factory = remember {
    viewModelFactory {
        val component = MainComponent()
        initializer {
            MainViewModel(
                navController = navController,
                locationRepository = component.locationListRepository,
            )
        }
    }
}
