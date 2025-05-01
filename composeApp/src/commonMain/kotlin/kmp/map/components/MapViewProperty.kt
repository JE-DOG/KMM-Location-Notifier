package kmp.map.components

import ru.khinkal.locationNotifier.core.location.model.GeoPoint

object MapViewProperty {
    const val MAX_ZOOM = 5_000.0
    const val MIN_ZOOM = 100_000_000.0
    const val START_ZOOM = 10_000.0

    const val TILE_OVERLAY = "https://tile.openstreetmap.org/{z}/{x}/{y}.png"

    // TODO: LN-23 - Add button which set map location by user geolocation and zoom controllers
    val START_BASE_GEO_POINT = GeoPoint(latitude = 42.974425, longitude = 47.499865)
}
