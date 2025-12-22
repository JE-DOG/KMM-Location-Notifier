package ru.khinkal.locationNotifier.core.map

import ru.khinkal.locationNotifier.core.map.components.MapController
import ru.khinkal.locationNotifier.core.map.components.MapViewHandler
import ru.khinkal.locationNotifier.core.map.components.MapViewMarkerLayer

abstract class MapViewManager {

    abstract val controller: MapController
    abstract val markerLayer: MapViewMarkerLayer
    abstract val handler: MapViewHandler
}
