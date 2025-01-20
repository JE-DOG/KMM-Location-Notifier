package ru.khinkal.locationNotifier.feature.setGeoPoint.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import map.components.MapViewProperty
import map.model.MapViewMarker
import map.rememberMapViewManager
import ru.khinkal.locationNotifier.core.location.model.BaseGeoPoint

@Composable
fun SetGeoPointContent(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    onConfirm: (BaseGeoPoint) -> Unit,
) {
    val mapViewManager = rememberMapViewManager()
    var selectedGeoPoint: BaseGeoPoint? by remember {
        mutableStateOf(null)
    }

    LaunchedEffect(Unit) {
        with(mapViewManager.controller) {
            setCenter(MapViewProperty.START_BASE_GEO_POINT)
            zoomTo(MapViewProperty.START_ZOOM)
        }
        mapViewManager.handler.setOnClickListener { baseGeoPoint ->
            selectedGeoPoint = baseGeoPoint
        }
    }

    LaunchedEffect(selectedGeoPoint) {
        val selectedGeoPoint = selectedGeoPoint ?: return@LaunchedEffect
        val marker = MapViewMarker(
            id = "1",
            baseGeoPoint = selectedGeoPoint,
        )
        mapViewManager.markerLayer.addMarker(marker)
        mapViewManager.controller.moveTo(selectedGeoPoint)
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
                    .padding(horizontal = 16.dp),
                onClick = {
                    onConfirm(selectedGeoPoint)
                },
            )
        }
    ) { _ ->
        mapViewManager.Content(
            Modifier.fillMaxSize(),
        )
    }
}
