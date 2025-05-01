package kmp.map

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kmp.map.components.MapViewController
import kmp.map.components.MapViewHandler
import kmp.map.components.MapViewMarkerLayer
import kmp.map.components.MapViewProperty

// TODO: Put everything what not with expect modifier to default directory
// TODO: LN-13 - Rebuild map( (Tech-debt iteration 3)
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
