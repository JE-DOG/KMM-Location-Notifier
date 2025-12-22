package ru.khinkal.locationNotifier.feature.main.data.storage.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint

@Entity(tableName = GoalGeoPointEntity.TABLE_NAME)
data class GoalGeoPointEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val meters: Int = 0,
    @Embedded val geoPoint: GeoPointEntity,
) {

    fun toDomain(): GoalGeoPoint = GoalGeoPoint(
        id = id,
        name = name,
        meters = meters,
        geoPoint = geoPoint.toDomain(),
    )

    companion object {

        const val TABLE_NAME = "geo_point_table"

        fun fromDomain(
            goalGeoPointDomain: GoalGeoPoint
        ): GoalGeoPointEntity = goalGeoPointDomain.run {
            GoalGeoPointEntity(
                id = id,
                name = name,
                meters = meters,
                geoPoint = GeoPointEntity.fromDomain(geoPoint),
            )
        }
    }
}
