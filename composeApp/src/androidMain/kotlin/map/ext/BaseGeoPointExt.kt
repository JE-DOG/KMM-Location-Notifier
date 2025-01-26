package map.ext

import io.openmobilemaps.mapscore.shared.map.coordinates.Coord
import io.openmobilemaps.mapscore.shared.map.coordinates.CoordinateSystemIdentifiers
import ru.khinkal.locationNotifier.core.location.model.BaseGeoPoint

fun BaseGeoPoint.toCoord(zoom: Double = 0.0): Coord =
    Coord(
        systemIdentifier = CoordinateSystemIdentifiers.EPSG4326(),
        x = longitude,
        y = latitude,
        z = zoom,
    )
