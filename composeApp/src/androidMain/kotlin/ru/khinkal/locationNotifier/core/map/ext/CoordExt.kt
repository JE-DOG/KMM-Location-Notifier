package ru.khinkal.locationNotifier.core.map.ext

import org.maplibre.android.geometry.LatLng
import ru.khinkal.locationNotifier.core.location.model.GeoPoint

fun LatLng.toGeoPoint(): GeoPoint =
    GeoPoint(
        latitude = latitude,
        longitude = longitude,
    )
