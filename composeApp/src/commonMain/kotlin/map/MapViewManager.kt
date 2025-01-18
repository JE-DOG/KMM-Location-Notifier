package map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import map.components.MapViewController
import map.components.MapViewMarkerLayer
import map.components.MapViewProperty
import ru.khinkal.locationNotifier.core.location.model.BaseGeoPoint

abstract class MapViewManager {

    private var onClickListener: ((BaseGeoPoint) -> Unit)? = null

    abstract val controller: MapViewController
    abstract val markerLayer: MapViewMarkerLayer

    @Composable
    abstract fun Content(modifier: Modifier)

    fun setOnClickListener(onClick: (BaseGeoPoint) -> Unit) {
        onClickListener = onClick
    }
}

expect fun createMapViewManager(): MapViewManager

@Composable
fun rememberMapViewManager(
    onClick: (BaseGeoPoint) -> Unit = {},
): MapViewManager {
    return remember {
        createMapViewManager().apply {
            with(controller) {
                setMaxZoom(MapViewProperty.MAX_ZOOM)
                setMinZoom(MapViewProperty.MIN_ZOOM)
            }
            setOnClickListener(onClick)
        }
    }
}
