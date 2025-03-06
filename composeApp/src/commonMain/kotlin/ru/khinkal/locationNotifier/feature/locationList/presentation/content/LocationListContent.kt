package ru.khinkal.locationNotifier.feature.locationList.presentation.content

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.khinkal.locationNotifier.feature.locationList.presentation.components.LocationListFloatingButton
import ru.khinkal.locationNotifier.feature.locationList.presentation.components.LocationListToolBar
import ru.khinkal.locationNotifier.feature.locationList.presentation.content.main.LocationListMainContent
import ru.khinkal.locationNotifier.feature.locationList.presentation.vm.model.LocationListAction
import ru.khinkal.locationNotifier.feature.locationList.presentation.vm.model.LocationListState

// TODO: Content dependence on state error and loading
@Composable
fun LocationListContent(
    state: LocationListState,
    sendAction: (LocationListAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            LocationListToolBar(
                onSettingsClick = {
                    val action = LocationListAction.OnSettingsClick()
                    sendAction(action)
                },
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            LocationListFloatingButton(
                onAddClick = {
                    val action = LocationListAction.OnAddLocationClick()
                    sendAction(action)
                },
            )
        },
    ) { paddingValues ->
        LocationListMainContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            geoPoints = state.geoPoints,
            sendAction = sendAction,
        )
    }
}
