package map.ext

import io.openmobilemaps.mapscore.shared.map.coordinates.Coord
import ru.khinkal.locationNotifier.core.location.model.BaseGeoPoint

fun Coord.toBaseGeoPoint(): BaseGeoPoint =
    BaseGeoPoint(
        latitude = y,
        longitude = x,
    )
