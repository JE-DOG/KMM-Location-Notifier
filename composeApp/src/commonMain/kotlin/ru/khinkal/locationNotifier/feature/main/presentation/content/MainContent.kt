package ru.khinkal.locationNotifier.feature.main.presentation.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Label
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kmm_location_notifier.composeapp.generated.resources.Res
import kmm_location_notifier.composeapp.generated.resources.main_screen_add_location_tooltip
import org.jetbrains.compose.resources.stringResource
import ru.khinkal.locationNotifier.feature.main.presentation.content.components.ActiveGoalProgressContent
import ru.khinkal.locationNotifier.feature.main.presentation.content.components.LocationListFloatingButton
import ru.khinkal.locationNotifier.feature.main.presentation.content.components.LocationListToolBar
import ru.khinkal.locationNotifier.feature.main.presentation.content.content.MainEmptyLocationsContent
import ru.khinkal.locationNotifier.feature.main.presentation.content.locations.LocationsList
import ru.khinkal.locationNotifier.feature.main.presentation.vm.model.MainAction
import ru.khinkal.locationNotifier.feature.main.presentation.vm.model.MainState

// TODO: Content depends on state error and loading
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(
    state: MainState,
    sendAction: (MainAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val onAddLocationClick = {
        val action = MainAction.OnAddLocationClick
        sendAction(action)
    }
    val isGoalGeoPointsEmpty = state.goalGeoPoints.isEmpty()

    Scaffold(
        modifier = modifier,
        topBar = {
            LocationListToolBar()
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            Label(
                isPersistent = isGoalGeoPointsEmpty,
                label = {
                    PlainTooltip(
                        shape = RoundedCornerShape(12.dp),
                        containerColor = MaterialTheme.colorScheme.inverseSurface,
                    ) {
                        Text(
                            text = stringResource(Res.string.main_screen_add_location_tooltip),
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.inverseOnSurface,
                        )
                    }
                },
            ) {
                LocationListFloatingButton(
                    onAddClick = onAddLocationClick,
                )
            }
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

            if (isGoalGeoPointsEmpty) {
                MainEmptyLocationsContent(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                )
            } else {
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
}
