package kmp.map.components

import io.openmobilemaps.mapscore.map.view.MapView
import kmp.map.ext.toCoord
import ru.khinkal.locationNotifier.core.location.model.GeoPoint

class AndroidMapViewController(
    private val mapView: MapView,
): MapViewController {

    private val camera get() = mapView.getCamera()

    init {
        camera.setBoundsRestrictWholeVisibleRect(true)
    }

    override fun getZoom(): Double = camera.getZoom()

    override fun setMaxZoom(zoom: Double) = camera.setMaxZoom(zoom)

    override fun setMinZoom(zoom: Double) = camera.setMinZoom(zoom)

    override fun zoomTo(zoom: Double) = camera.setZoom(zoom, false)

    override fun setCenter(geoPoint: GeoPoint, zoom: Double) =
        moveTo(geoPoint, zoom, false)

    override fun moveTo(geoPoint: GeoPoint, zoom: Double) =
        // TODO: Make animated true when map animation will work normally
        moveTo(geoPoint, zoom, false)

    private fun moveTo(geoPoint: GeoPoint, zoom: Double, animated: Boolean) {
        val coord = geoPoint.toCoord()
        camera.moveToCenterPositionZoom(coord, zoom, animated)
    }
}
