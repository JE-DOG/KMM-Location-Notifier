package ru.khinkal.locationNotifier.core.map.components

import ru.khinkal.locationNotifier.core.map.model.MapViewMarker

interface MapViewMarkerLayer {

    fun addMarker(marker: MapViewMarker)
}
