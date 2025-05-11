package ru.khinkal.locationNotifier.core.map.components

import ru.khinkal.locationNotifier.core.location.model.GeoPoint

class MapViewHandler {

    private var clickListener: (GeoPoint) -> Unit = {}

    fun setOnClickListener(action: (GeoPoint) -> Unit) { clickListener = action }

    fun onClick(geoPoint: GeoPoint) {
        clickListener(geoPoint)
    }
}
