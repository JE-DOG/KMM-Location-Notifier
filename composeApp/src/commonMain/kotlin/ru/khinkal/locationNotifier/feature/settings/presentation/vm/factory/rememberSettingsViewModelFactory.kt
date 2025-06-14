package ru.khinkal.locationNotifier.feature.settings.presentation.vm.factory

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavController
import ru.khinkal.locationNotifier.feature.settings.presentation.di.SettingsComponent
import ru.khinkal.locationNotifier.feature.settings.presentation.vm.SettingsViewModel

@Composable
fun rememberSettingsViewModelFactory(
    navController: NavController,
): ViewModelProvider.Factory = remember {
    viewModelFactory {
        val component = SettingsComponent()
        initializer {
            SettingsViewModel(
                navController = navController,
                settingsManager = component.settingsManager,
            )
        }
    }
}
