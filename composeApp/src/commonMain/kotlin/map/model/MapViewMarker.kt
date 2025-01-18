package map.model

import ru.khinkal.locationNotifier.core.location.model.BaseGeoPoint

data class MapViewMarker(
    val id: String,
    val baseGeoPoint: BaseGeoPoint,
)
