package map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import map.components.MapViewController
import map.components.MapViewHandler
import map.components.MapViewMarkerLayer
import map.provider.IosMapViewDepsProvider

class IosMapViewManager: MapViewManager() {

    private val deps = IosMapViewDepsProvider.INSTANCE.create()

    val view get() = deps.viewController.view
    override val controller: MapViewController = deps.controller
    override val markerLayer: MapViewMarkerLayer = deps.markerLayer
    override val handler: MapViewHandler = deps.handler
}

@Composable
actual fun ExpectMap(
    modifier: Modifier,
    mapViewManagerUpdate: (MapViewManager) -> Unit
) {
    var mapViewManager: IosMapViewManager? = remember {
        null
    }

    UIKitView(
        modifier = modifier,
        factory = {
            val newMapViewManager = IosMapViewManager()
            mapViewManager = newMapViewManager
            newMapViewManager.view
        },
        update = {
            mapViewManagerUpdate(mapViewManager ?: return@UIKitView)
        },
    )
}
