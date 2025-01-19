package map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import map.components.MapViewController
import map.components.MapViewHandler
import map.components.MapViewMarkerLayer
import map.components.MapViewProperty

abstract class MapViewManager {

    abstract val controller: MapViewController
    abstract val markerLayer: MapViewMarkerLayer
    abstract val handler: MapViewHandler

    @Composable
    abstract fun Content(modifier: Modifier)
}

expect fun createMapViewManager(): MapViewManager

@Composable
fun rememberMapViewManager(): MapViewManager {
    return remember {
        createMapViewManager().apply {
            with(controller) {
                setMaxZoom(MapViewProperty.MAX_ZOOM)
                setMinZoom(MapViewProperty.MIN_ZOOM)
            }
        }
    }
}
