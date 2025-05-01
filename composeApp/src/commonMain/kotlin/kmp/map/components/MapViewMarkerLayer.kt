package kmp.map.components

import kmp.map.model.MapViewMarker

interface MapViewMarkerLayer {

    fun addMarker(marker: MapViewMarker)
}
