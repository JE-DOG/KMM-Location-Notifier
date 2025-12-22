package ru.khinkal.locationNotifier.core.map.provider

import ru.khinkal.locationNotifier.core.map.components.MapController
import ru.khinkal.locationNotifier.core.map.components.MapViewHandler
import ru.khinkal.locationNotifier.core.map.components.MapViewMarkerLayer
import platform.UIKit.UIViewController

interface IosMapViewProvider {
    val viewController: UIViewController
    val controller: MapController
    val markerLayer: MapViewMarkerLayer
    val handler: MapViewHandler
}
