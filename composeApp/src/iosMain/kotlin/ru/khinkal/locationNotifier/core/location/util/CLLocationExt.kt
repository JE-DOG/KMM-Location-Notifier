package ru.khinkal.locationNotifier.core.location.util

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.MemScope
import kotlinx.cinterop.pointed
import platform.CoreLocation.CLLocation
import ru.khinkal.locationNotifier.core.location.model.GeoPoint

@OptIn(ExperimentalForeignApi::class)
fun CLLocation.toGeoPoint(): GeoPoint {
    val coordinate = coordinate.getPointer(MemScope())
    val point = coordinate.pointed
    return GeoPoint(
        latitude = point.latitude,
        longitude = point.longitude,
    )
}
