package kmp.map.model

import ru.khinkal.locationNotifier.core.location.model.GeoPoint

data class MapViewMarker(
    val id: String,
    val geoPoint: GeoPoint,
)
