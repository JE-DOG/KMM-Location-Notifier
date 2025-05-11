package ru.khinkal.locationNotifier.core.map.components

import io.openmobilemaps.mapscore.map.view.MapView
import ru.khinkal.locationNotifier.core.location.model.GeoPoint
import ru.khinkal.locationNotifier.core.map.ext.toCoord

class AndroidMapController(
    private val mapView: MapView,
) : MapController {

    private val camera get() = mapView.getCamera()

    init {
        camera.setBoundsRestrictWholeVisibleRect(true)
    }

    override fun getZoom(): Double = camera.getZoom()

    override fun setMaxZoom(zoom: Double) = camera.setMaxZoom(zoom)

    override fun setMinZoom(zoom: Double) = camera.setMinZoom(zoom)

    override fun zoomTo(zoom: Double) = camera.setZoom(zoom, false)

    override fun setCenter(geoPoint: GeoPoint, zoom: Double) {}

    override fun moveTo(geoPoint: GeoPoint, zoom: Double) {}

    private fun moveTo(geoPoint: GeoPoint, zoom: Double, animated: Boolean) {
        val coord = geoPoint.toCoord()
        camera.moveToCenterPositionZoom(coord, zoom, animated)
    }
}
