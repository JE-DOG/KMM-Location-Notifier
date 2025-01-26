package map

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import map.components.MapViewController
import map.components.MapViewHandler
import map.components.MapViewMarkerLayer
import map.components.MapViewProperty

abstract class MapViewManager {

    abstract val controller: MapViewController
    abstract val markerLayer: MapViewMarkerLayer
    abstract val handler: MapViewHandler
}

@Composable
fun Map(
    modifier: Modifier,
    mapViewManagerUpdate: (MapViewManager) -> Unit,
) {
    ExpectMap(
        modifier = modifier,
        mapViewManagerUpdate = { mapViewManager ->
            mapViewManager.apply {
                with(controller) {
                    setMaxZoom(MapViewProperty.MAX_ZOOM)
                    setMinZoom(MapViewProperty.MIN_ZOOM)
                }
            }
            mapViewManagerUpdate(mapViewManager)
        },
    )
}

@Composable
expect fun ExpectMap(
    modifier: Modifier,
    mapViewManagerUpdate: (MapViewManager) -> Unit,
)
