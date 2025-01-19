package map

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import map.components.MapViewController
import map.components.MapViewHandler
import map.components.MapViewMarkerLayer
import map.provider.IosMapViewDepsProvider

class IosMapViewManager: MapViewManager() {

    private val deps = IosMapViewDepsProvider.INSTANCE

    override val controller: MapViewController = deps.controller
    override val markerLayer: MapViewMarkerLayer = deps.markerLayer
    override val handler: MapViewHandler = deps.handler

    @Composable
    override fun Content(modifier: Modifier) {
        UIKitView(
            modifier = modifier,
            factory = {
                deps.viewController.view
            },
        )
    }
}

actual fun createMapViewManager(): MapViewManager = IosMapViewManager()
