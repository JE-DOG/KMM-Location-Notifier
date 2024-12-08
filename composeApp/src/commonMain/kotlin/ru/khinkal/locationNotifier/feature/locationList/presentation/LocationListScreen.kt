package ru.khinkal.locationNotifier.feature.locationList.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import ru.khinkal.locationNotifier.feature.locationList.presentation.components.LocationListContent
import ru.khinkal.locationNotifier.feature.locationList.presentation.vm.LocationListViewModel

@Composable
fun LocationListScreen(
    viewModel: LocationListViewModel,
    modifier: Modifier = Modifier,
) {
    val state by viewModel.state.collectAsState()

    LocationListContent(
        modifier = modifier,
        state = state,
        sendAction = viewModel::action
    )
}