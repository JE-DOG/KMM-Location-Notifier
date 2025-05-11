package ru.khinkal.locationNotifier.core.map

import io.openmobilemaps.mapscore.map.layers.TiledRasterLayer
import io.openmobilemaps.mapscore.map.view.MapView
import io.openmobilemaps.mapscore.shared.graphics.common.Color
import io.openmobilemaps.mapscore.shared.map.coordinates.Coord
import io.openmobilemaps.mapscore.shared.map.layers.tiled.raster.Tiled2dMapRasterLayerCallbackInterface
import ru.khinkal.locationNotifier.core.map.components.AndroidMapController
import ru.khinkal.locationNotifier.core.map.components.AndroidMapViewMarkerLayer
import ru.khinkal.locationNotifier.core.map.components.MapController
import ru.khinkal.locationNotifier.core.map.components.MapViewHandler
import ru.khinkal.locationNotifier.core.map.components.MapViewMarkerLayer
import ru.khinkal.locationNotifier.core.map.components.MapViewProperty
import ru.khinkal.locationNotifier.core.map.ext.toBaseGeoPoint

class AndroidMapViewManager(
    private val mapView: MapView,
) : MapViewManager() {

    private val context get() = mapView.context

    override val controller: MapController = AndroidMapController(mapView)
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
        mapView.mapInterface?.setBackgroundColor(TRANSPARENT_COLOR)
        val tiledRasterLayer = TiledRasterLayer(
            context = context,
            tileUrl = MapViewProperty.STYLE_URL,
        )
        mapView.addLayer(tiledRasterLayer)
        tiledRasterLayer.rasterLayerInterface()
            .setCallbackHandler(mapViewOverlayHandler)
    }

    companion object {

        private val TRANSPARENT_COLOR get() = Color(0f, 0f, 0f, 100f)
    }
}