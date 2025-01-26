package map.components

import io.openmobilemaps.mapscore.map.view.MapView
import map.ext.toCoord
import ru.khinkal.locationNotifier.core.location.model.BaseGeoPoint

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

    override fun setCenter(baseGeoPoint: BaseGeoPoint, zoom: Double) =
        moveTo(baseGeoPoint, zoom, false)

    override fun moveTo(baseGeoPoint: BaseGeoPoint, zoom: Double) =
        moveTo(baseGeoPoint, zoom, true)

    private fun moveTo(baseGeoPoint: BaseGeoPoint, zoom: Double, animated: Boolean) {
        val coord = baseGeoPoint.toCoord()
        camera.moveToCenterPositionZoom(coord, zoom, animated)
    }
}
