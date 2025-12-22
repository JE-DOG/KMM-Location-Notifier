package ru.khinkal.locationNotifier.feature.main.data.storage.model

import ru.khinkal.locationNotifier.core.location.model.GeoPoint

data class GeoPointEntity(
    val longitude: Double,
    val latitude: Double,
) {

    companion object {

        fun fromDomain(geoPoint: GeoPoint): GeoPointEntity {
            return with(geoPoint) {
                GeoPointEntity(
                    longitude = longitude,
                    latitude = latitude,
                )
            }
        }
    }
}

fun GeoPointEntity.toDomain(): GeoPoint {
    return GeoPoint(
        latitude = latitude,
        longitude = longitude,
    )
}
