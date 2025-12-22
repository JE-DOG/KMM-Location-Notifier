package ru.khinkal.locationNotifier.feature.setGeoPoint.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.khinkal.locationNotifier.core.location.model.GeoPoint
import ru.khinkal.locationNotifier.core.map.components.MapViewProperty
import ru.khinkal.locationNotifier.core.map.model.MapViewMarker
import ru.khinkal.locationNotifier.core.map.ui.Map

private const val SET_GEO_POINT_MARKER_ID = "Set geo point marker"

@Composable
fun SetGeoPointContent(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    onConfirm: (GeoPoint) -> Unit,
) {
    var selectedGeoPoint: GeoPoint? by remember {
        mutableStateOf(null)
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            SetGeoPointTopBar(
                onBackClicked = onBackClicked,
            )
        },
        bottomBar = {
            val selectedGeoPoint = selectedGeoPoint ?: return@Scaffold

            SetGeoPointBottomBar(
                modifier = Modifier
                    .navigationBarsPadding()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp),
                onClick = {
                    onConfirm(selectedGeoPoint)
                },
            )
        },
    ) { _ ->
        Map(
            modifier = Modifier.fillMaxSize(),
            initialize = { mapViewManager ->
                with(mapViewManager.controller) {
                    setCenter(
                        geoPoint = MapViewProperty.START_GEO_POINT,
                        zoom = MapViewProperty.START_ZOOM,
                    )
                }
                mapViewManager.handler.setOnClickListener { baseGeoPoint ->
                    selectedGeoPoint = baseGeoPoint
                }
            },
            update = { mapViewManager ->
                val selectedGeoPoint = selectedGeoPoint
                if (selectedGeoPoint != null) {
                    val marker = MapViewMarker(
                        id = SET_GEO_POINT_MARKER_ID,
                        geoPoint = selectedGeoPoint,
                    )
                    mapViewManager.markerLayer.addMarker(marker)
                    mapViewManager.controller.moveTo(selectedGeoPoint)
                }
            },
        )
    }
}
