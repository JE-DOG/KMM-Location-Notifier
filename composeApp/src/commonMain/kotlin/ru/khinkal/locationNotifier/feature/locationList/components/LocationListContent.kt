package ru.khinkal.locationNotifier.feature.locationList.components

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
import ru.khinkal.locationNotifier.feature.locationList.vm.LocationListState

@Composable
fun LocationListContent(
    state: LocationListState,
    onSetGeoPointButtonClick: () -> Unit,
    onSettingsButtonClick: () -> Unit,
    onChangeLocationButtonClick: () -> Unit,
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
                onClick = onChangeLocationButtonClick,
            ) {
                Text(
                    text = if (isAdd) "Add location" else "Remove location",
                )
            }

            Button(
                onClick = onSetGeoPointButtonClick,
            ) {
                Text(
                    text = "Set geo point",
                )
            }

            Button(
                onClick = onSettingsButtonClick,
            ) {
                Text(
                    text = "Settings",
                )
            }
        }
    }
}