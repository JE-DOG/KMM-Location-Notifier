package kmp.map.ext

import io.openmobilemaps.mapscore.shared.map.coordinates.Coord
import ru.khinkal.locationNotifier.core.location.model.GeoPoint

fun Coord.toBaseGeoPoint(): GeoPoint =
    GeoPoint(
        latitude = y,
        longitude = x,
    )
