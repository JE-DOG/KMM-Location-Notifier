package ru.khinkal.locationNotifier.core.map.components

import KMM_Location_Notifier.composeApp.BuildConfig
import ru.khinkal.locationNotifier.core.location.model.GeoPoint

object MapViewProperty {
    const val MAX_ZOOM = 20.0
    const val MIN_ZOOM = 3.0
    const val START_ZOOM = 12.0

    const val STYLE_URL = BuildConfig.STYLE_URL

    // TODO: LN-23 - Add button which set map location by user geolocation and zoom controllers
    val START_GEO_POINT
        get() = GeoPoint(
            latitude = 42.974425,
            longitude = 47.499865,
        )
}
