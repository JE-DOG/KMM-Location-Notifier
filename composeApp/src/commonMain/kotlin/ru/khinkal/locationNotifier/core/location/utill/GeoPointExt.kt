package ru.khinkal.locationNotifier.core.location.utill

import ru.khinkal.locationNotifier.core.location.model.BaseGeoPoint
import ru.khinkal.locationNotifier.feature.locationList.domain.model.GeoPoint
import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

private const val RADIANS = 0.017453292519943295

private fun toRadians(agree: Double) = agree * RADIANS

infix fun BaseGeoPoint.distanceInMeters(point: BaseGeoPoint): Int {
    val lon1 = toRadians(point.longitude)
    val lon2 = toRadians(longitude)
    val lat1 = toRadians(point.latitude)
    val lat2 = toRadians(latitude)

    val dlon = lon2 - lon1
    val dlat = lat2 - lat1
    val a = (sin(dlat / 2).pow(2.0)
            + (cos(lat1) * cos(lat2)
            * sin(dlon / 2).pow(2.0)))
    val c = 2 * asin(sqrt(a))
    val r = 6371.0

    return (c * r * 1000).toInt()
}