package map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import io.openmobilemaps.mapscore.map.layers.TiledRasterLayer
import io.openmobilemaps.mapscore.map.view.MapView
import io.openmobilemaps.mapscore.shared.map.MapConfig
import io.openmobilemaps.mapscore.shared.map.coordinates.Coord
import io.openmobilemaps.mapscore.shared.map.coordinates.CoordinateSystemFactory
import io.openmobilemaps.mapscore.shared.map.layers.tiled.raster.Tiled2dMapRasterLayerCallbackInterface
import map.components.AndroidMapViewController
import map.components.AndroidMapViewMarkerLayer
import map.components.MapViewController
import map.components.MapViewHandler
import map.components.MapViewMarkerLayer
import map.components.MapViewProperty
import map.ext.toBaseGeoPoint

class AndroidMapViewManager(
    private val mapView: MapView,
) : MapViewManager() {

    private val context get() = mapView.context

    override val controller: MapViewController = AndroidMapViewController(mapView)
    override val markerLayer: MapViewMarkerLayer by lazy {
        AndroidMapViewMarkerLayer(mapView)
    }
    override val handler: MapViewHandler = MapViewHandler()

    private val mapViewOverlayHandler =
        object : Tiled2dMapRasterLayerCallbackInterface() {
            override fun onClickConfirmed(coord: Coord): Boolean {
                coord.systemIdentifier
                val baseGeoPoint = coord.toBaseGeoPoint()
                handler.onClick(baseGeoPoint)
                return true
            }

            override fun onLongPress(coord: Coord): Boolean = false
        }

    init {
        addTileOverlay()
    }

    private fun addTileOverlay() {
        val tiledRasterLayer = TiledRasterLayer(
            context = context,
            tileUrl = MapViewProperty.TILE_OVERLAY,
        )
        mapView.addLayer(tiledRasterLayer)
        tiledRasterLayer.rasterLayerInterface()
            .setCallbackHandler(mapViewOverlayHandler)
    }
}

@Composable
actual fun ExpectMap(
    modifier: Modifier,
    mapViewManagerUpdate: (MapViewManager) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var mapViewManager: MapViewManager? = remember {
        null
    }

    AndroidView(
        modifier = modifier,
        factory = { context ->
            MapView(context).apply {
                setupMap(MapConfig(CoordinateSystemFactory.getEpsg4326System()))
                registerLifecycle(lifecycleOwner.lifecycle)
                mapViewManager = AndroidMapViewManager(this)
            }
        },
        update = {
            mapViewManagerUpdate(mapViewManager ?: return@AndroidView)
        },
    )
}
