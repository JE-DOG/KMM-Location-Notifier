package ru.khinkal.locationNotifier.feature.main.presentation.content

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.khinkal.locationNotifier.feature.main.presentation.content.components.LocationListFloatingButton
import ru.khinkal.locationNotifier.feature.main.presentation.content.components.LocationListToolBar
import ru.khinkal.locationNotifier.feature.main.presentation.content.locations.LocationsList
import ru.khinkal.locationNotifier.feature.main.presentation.vm.model.MainAction
import ru.khinkal.locationNotifier.feature.main.presentation.vm.model.MainState

// TODO: Content depends on state error and loading
@Composable
fun MainContent(
    state: MainState,
    sendAction: (MainAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            LocationListToolBar(
                onSettingsClick = {
                    val action = MainAction.OnSettingsClick
                    sendAction(action)
                },
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            LocationListFloatingButton(
                onAddClick = {
                    val action = MainAction.OnAddLocationClick
                    sendAction(action)
                },
            )
        },
    ) { paddingValues ->
        LocationsList(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            goalGeoPoints = state.goalGeoPoints,
            sendAction = sendAction,
        )
    }
}
