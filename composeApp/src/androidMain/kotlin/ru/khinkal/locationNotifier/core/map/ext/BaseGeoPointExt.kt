package ru.khinkal.locationNotifier.core.map.ext

import org.maplibre.android.geometry.LatLng
import ru.khinkal.locationNotifier.core.location.model.GeoPoint

fun GeoPoint.toLatLng(): LatLng =
    LatLng(
        longitude = longitude,
        latitude = latitude,
    )
