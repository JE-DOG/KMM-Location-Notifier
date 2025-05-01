package ru.khinkal.locationNotifier.feature.main.presentation.content.locations

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.khinkal.locationNotifier.feature.main.domain.model.GeoPoint
import ru.khinkal.locationNotifier.feature.main.presentation.vm.model.MainAction

@Composable
fun LocationsList(
    geoPoints: List<GeoPoint>,
    sendAction: (MainAction) -> Unit,
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
            modifier = Modifier
                .padding(bottom = 5.dp),
            geoPoints = geoPoints,
            onItemClick = { geoPoint ->
                val action = MainAction.OnLocationClick(geoPoint)
                sendAction(action)
            },
        )
    }
}
