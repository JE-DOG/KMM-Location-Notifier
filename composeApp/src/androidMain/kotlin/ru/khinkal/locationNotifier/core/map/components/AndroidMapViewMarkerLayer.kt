package ru.khinkal.locationNotifier.core.map.components

import org.maplibre.android.annotations.MarkerOptions
import org.maplibre.android.maps.MapView
import ru.khinkal.locationNotifier.core.map.ext.toLatLng
import ru.khinkal.locationNotifier.core.map.model.MapViewMarker

class AndroidMapViewMarkerLayer(
    private val mapView: MapView,
) : MapViewMarkerLayer {

    private val stringIdToLongId by lazy { hashMapOf<String, Long>() }

    override fun addMarker(marker: MapViewMarker) {
        if (marker.id in stringIdToLongId) {
            updateMarker(marker)
        } else {
            createMarker(marker)
        }
    }

    private fun updateMarker(marker: MapViewMarker) {
        mapView.getMapAsync { map ->
            val updatedMapMarker = map.markers.find { stringIdToLongId[marker.id] == it.id }
            requireNotNull(updatedMapMarker).apply {
                position = marker.geoPoint.toLatLng()
            }
            map.updateMarker(updatedMapMarker)
        }
    }

    private fun createMarker(marker: MapViewMarker) {
        mapView.getMapAsync { map ->
            val markerOption = MarkerOptions().apply {
                position = marker.geoPoint.toLatLng()
            }
            val mapMarker = map.addMarker(markerOption)
            stringIdToLongId[marker.id] = mapMarker.id
        }
    }
}
