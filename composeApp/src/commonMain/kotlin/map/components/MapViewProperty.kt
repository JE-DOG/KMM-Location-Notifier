package map.components

import ru.khinkal.locationNotifier.core.location.model.BaseGeoPoint

object MapViewProperty {
    const val MAX_ZOOM = 5_000.0
    const val MIN_ZOOM = 100_000_000.0

    const val TILE_OVERLAY = "https://tile.openstreetmap.org/{z}/{x}/{y}.png"

    val START_BASE_GEO_POINT = BaseGeoPoint(latitude = 42.974425, longitude = 47.499865)
}
