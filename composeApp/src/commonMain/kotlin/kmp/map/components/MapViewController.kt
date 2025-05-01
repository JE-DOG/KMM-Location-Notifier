package kmp.map.components

import ru.khinkal.locationNotifier.core.location.model.GeoPoint

interface MapViewController {

    fun getZoom(): Double
    fun setMaxZoom(zoom: Double)
    fun setMinZoom(zoom: Double)

    fun zoomTo(zoom: Double)

    fun setCenter(geoPoint: GeoPoint, zoom: Double = getZoom())
    fun moveTo(geoPoint: GeoPoint, zoom: Double = getZoom())
}
