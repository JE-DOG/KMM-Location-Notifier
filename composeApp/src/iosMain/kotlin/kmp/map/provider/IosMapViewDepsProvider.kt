package kmp.map.provider

import kmp.map.components.MapViewController
import kmp.map.components.MapViewHandler
import kmp.map.components.MapViewMarkerLayer
import platform.UIKit.UIViewController
import kotlin.properties.Delegates.notNull

interface IosMapViewDepsProvider {
    val viewController: UIViewController
    val controller: MapViewController
    val markerLayer: MapViewMarkerLayer
    val handler: MapViewHandler

    companion object {
        var INSTANCE: IosMapViewDepsProviderFactory by notNull()
    }
}

interface IosMapViewDepsProviderFactory {

    fun create(): IosMapViewDepsProvider
}
