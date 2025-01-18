package map

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import map.components.MapViewController
import map.components.MapViewMarkerLayer

class AndroidMapViewManager: MapViewManager() {

    override val controller: MapViewController
        get() = TODO("Not yet implemented")
    override val markerLayer: MapViewMarkerLayer
        get() = TODO("Not yet implemented")

    @Composable
    override fun Content(modifier: Modifier) {
    }
}

actual fun createMapViewManager(): MapViewManager {
    TODO("Not yet implemented")
}