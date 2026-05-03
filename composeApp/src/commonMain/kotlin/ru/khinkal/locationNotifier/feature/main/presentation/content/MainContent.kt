package ru.khinkal.locationNotifier.feature.main.presentation.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.khinkal.locationNotifier.feature.main.presentation.content.components.ActiveGoalProgressContent
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
            LocationListToolBar()
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            val listeningProgress = state.locationListeningStatus
            if (listeningProgress != null) {
                ActiveGoalProgressContent(
                    modifier = Modifier.fillMaxWidth(),
                    locationListeningStatus = listeningProgress,
                    contentPadding = PaddingValues(
                        horizontal = 16.dp,
                        vertical = 10.dp,
                    ),
                )
            }

            LocationsList(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                goalGeoPoints = state.goalGeoPoints,
                sendAction = sendAction,
            )
        }
    }
}
