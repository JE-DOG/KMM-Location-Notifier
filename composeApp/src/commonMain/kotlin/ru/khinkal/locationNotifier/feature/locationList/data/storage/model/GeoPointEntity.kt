package ru.khinkal.locationNotifier.feature.locationList.data.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.khinkal.locationNotifier.feature.locationList.domain.model.GeoPoint

@Entity(
    tableName = "geo_point_table"
)
data class GeoPointEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val meters: Int = 0,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
){

    fun toDomain() = GeoPoint(
        id = id,
        name = name,
        meters = meters,
        latitude = latitude,
        longitude = longitude
    )

    companion object {

        fun fromDomain(
            geoPointDomain: GeoPoint
        ) = geoPointDomain.run {
            GeoPointEntity(
                id = id,
                name = name,
                latitude = latitude,
                longitude = longitude,
                meters = meters
            )
        }
    }
}