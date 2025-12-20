package ru.khinkal.locationNotifier.feature.createGoal.presentation.vm.model

import ru.khinkal.locationNotifier.core.location.model.GeoPoint
import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint

data class CreateGoalState(
    val name: String = "",
    val meters: Int? = null,
    val geoPoint: GeoPoint? = null,
    val canCreateGoal: Boolean = false,
) {

    companion object {

        fun initial(goalGeoPoint: GoalGeoPoint?): CreateGoalState {
            return if (goalGeoPoint != null) {
                CreateGoalState(
                    name = goalGeoPoint.name,
                    meters = goalGeoPoint.meters,
                    geoPoint = goalGeoPoint.geoPoint,
                    canCreateGoal = true,
                )
            } else {
                CreateGoalState()
            }
        }
    }
}
