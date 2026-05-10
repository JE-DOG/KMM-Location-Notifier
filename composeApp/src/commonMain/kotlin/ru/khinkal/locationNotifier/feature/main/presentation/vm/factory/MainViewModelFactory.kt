package ru.khinkal.locationNotifier.feature.main.presentation.vm.factory

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import ru.khinkal.locationNotifier.feature.main.presentation.di.createMainGraph
import ru.khinkal.locationNotifier.feature.main.presentation.vm.MainViewModel

@Composable
fun rememberMainViewModelFactory(): ViewModelProvider.Factory = remember {
    viewModelFactory {
        val graph = createMainGraph()

        initializer {
            MainViewModel(
                goalGeoPointRepository = graph.goalGeoPointRepository,
                goalGeoPointBroadcaster = graph.goalGeoPointBroadcaster,
            )
        }
    }
}
