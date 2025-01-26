package map.components

import ru.khinkal.locationNotifier.core.location.model.BaseGeoPoint

interface MapViewController {

    fun getZoom(): Double
    fun setMaxZoom(zoom: Double)
    fun setMinZoom(zoom: Double)

    fun zoomTo(zoom: Double)

    fun setCenter(baseGeoPoint: BaseGeoPoint, zoom: Double = getZoom())
    fun moveTo(baseGeoPoint: BaseGeoPoint, zoom: Double = getZoom())
}