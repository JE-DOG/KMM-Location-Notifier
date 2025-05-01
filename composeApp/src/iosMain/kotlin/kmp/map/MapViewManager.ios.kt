package kmp.map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import kmp.map.components.MapViewController
import kmp.map.components.MapViewHandler
import kmp.map.components.MapViewMarkerLayer
import kmp.map.provider.IosMapViewDepsProvider

// TODO: LN-13 - Rebuild map( (Tech-debt iteration 3)
class IosMapViewManager : MapViewManager() {

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
