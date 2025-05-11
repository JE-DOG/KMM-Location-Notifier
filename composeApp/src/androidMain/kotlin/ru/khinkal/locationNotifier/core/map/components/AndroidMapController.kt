package ru.khinkal.locationNotifier.core.map.components

import kotlinx.coroutines.runBlocking
import org.maplibre.android.camera.CameraUpdateFactory
import org.maplibre.android.maps.MapView
import ru.khinkal.locationNotifier.core.location.model.GeoPoint
import ru.khinkal.locationNotifier.core.map.ext.toLatLng

class AndroidMapController(
    private val mapView: MapView,
) : MapController {

    override fun getZoom(): Double {
        var zoom = 0.0
        runBlocking {
            mapView.getMapAsync { map ->
                zoom = map.zoom
            }
        }
        return zoom
    }

    override fun setMaxZoom(zoom: Double) {
        mapView.getMapAsync { map ->
            map.setMaxZoomPreference(zoom)
        }
    }

    override fun setMinZoom(zoom: Double) {
        mapView.getMapAsync { map ->
            map.setMinZoomPreference(zoom)
        }
    }

    override fun zoomTo(zoom: Double) {
        mapView.getMapAsync { map ->
            map.moveCamera(CameraUpdateFactory.zoomTo(zoom))
        }
    }

    override fun setCenter(geoPoint: GeoPoint, zoom: Double) {
        moveTo(
            geoPoint = geoPoint,
            zoom = zoom,
            animated = false,
        )
    }

    override fun moveTo(geoPoint: GeoPoint, zoom: Double) {
        moveTo(
            geoPoint = geoPoint,
            zoom = zoom,
            animated = true,
        )
    }

    private fun moveTo(geoPoint: GeoPoint, zoom: Double, animated: Boolean) {
        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(geoPoint.toLatLng(), zoom)
        mapView.getMapAsync { map ->
            if (animated) {
                map.animateCamera(cameraUpdate)
            } else {
                map.moveCamera(cameraUpdate)
            }
        }
    }
}
