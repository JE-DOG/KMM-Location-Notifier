package map.provider

import map.components.MapViewController
import map.components.MapViewMarkerLayer
import platform.UIKit.UIViewController
import kotlin.properties.Delegates.notNull

interface IosMapViewDepsProvider {
    val viewController: UIViewController
    val controller: MapViewController
    val markerLayer: MapViewMarkerLayer

    companion object {
        var INSTANCE: IosMapViewDepsProvider by notNull()
    }
}
