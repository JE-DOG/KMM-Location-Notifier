package map.components

import map.model.MapViewMarker

interface MapViewMarkerLayer {

    fun addMarker(marker: MapViewMarker)
}