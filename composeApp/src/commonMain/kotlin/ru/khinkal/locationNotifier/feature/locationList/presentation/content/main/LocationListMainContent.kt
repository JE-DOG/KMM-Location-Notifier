package ru.khinkal.locationNotifier.feature.locationList.presentation.content.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.khinkal.locationNotifier.feature.locationList.domain.model.GeoPoint
import ru.khinkal.locationNotifier.feature.locationList.presentation.vm.model.LocationListAction

@Composable
fun LocationListMainContent(
    geoPoints: List<GeoPoint>,
    sendAction: (LocationListAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 10.dp,
        ),
    ) {
        locations(
            geoPoints = geoPoints,
            onItemClick = { geoPoint ->
                val action = LocationListAction.OnLocationClick(geoPoint)
                sendAction(action)
            },
        )
    }
}
