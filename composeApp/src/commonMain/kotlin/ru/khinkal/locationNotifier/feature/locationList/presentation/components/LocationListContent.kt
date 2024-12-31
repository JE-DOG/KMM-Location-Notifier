package ru.khinkal.locationNotifier.feature.locationList.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.khinkal.locationNotifier.feature.locationList.domain.model.GeoPoint
import ru.khinkal.locationNotifier.feature.locationList.presentation.vm.model.LocationListAction
import ru.khinkal.locationNotifier.feature.locationList.presentation.vm.model.LocationListState

@Composable
fun LocationListContent(
    state: LocationListState,
    sendAction: (LocationListAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val isAdd = state.isAdd

    Scaffold(
        modifier = modifier,
    ) { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                onClick = {
                    val action = LocationListAction.ChangeIsAdd
                    sendAction(action)
                },
            ) {
                Text(
                    text = if (isAdd) "Add location" else "Remove location",
                )
            }

            Button(
                onClick = {
                    val action = LocationListAction.BroadcastLocation(
                        geoPoint = GeoPoint(
                            id = 1,
                            name = "Hello there",
                        )
                    )
                    sendAction(action)
                },
            ) {
                Text(
                    text = "Broadcast location",
                )
            }

            Button(
                onClick = {
                    val action = LocationListAction.NavigateTo.SetGeoPoint
                    sendAction(action)
                },
            ) {
                Text(
                    text = "Set geo point",
                )
            }

            Button(
                onClick = {
                    val action = LocationListAction.NavigateTo.Settings
                    sendAction(action)
                },
            ) {
                Text(
                    text = "Settings",
                )
            }
        }
    }
}