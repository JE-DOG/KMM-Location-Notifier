package ru.khinkal.locationNotifier.feature.setGeoPoint.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import map.components.MapViewProperty
import map.model.MapViewMarker
import map.rememberMapViewManager

@Composable
fun SetGeoPointContent(
    modifier: Modifier = Modifier,
) {
    val mapViewManager = rememberMapViewManager()

    LaunchedEffect(Unit) {
        with(mapViewManager.controller) {
            setCenter(MapViewProperty.START_BASE_GEO_POINT)
            zoomTo(MapViewProperty.START_ZOOM)
        }
        mapViewManager.handler.setOnClickListener { baseGeoPoint ->
            val marker = MapViewMarker(
                id = "1",
                baseGeoPoint = baseGeoPoint,
            )
            mapViewManager.markerLayer.addMarker(marker)
        }
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
    ) { _ ->
        mapViewManager.Content(
            Modifier.fillMaxSize(),
        )
    }
}