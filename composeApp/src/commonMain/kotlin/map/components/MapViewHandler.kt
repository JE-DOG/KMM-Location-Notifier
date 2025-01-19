package map.components

import ru.khinkal.locationNotifier.core.location.model.BaseGeoPoint

class MapViewHandler {

    private var clickListener: (BaseGeoPoint) -> Unit = {}

    fun setOnClickListener(action: (BaseGeoPoint) -> Unit) { clickListener = action }

    fun onClick(geoPoint: BaseGeoPoint) {
        clickListener(geoPoint)
    }
}