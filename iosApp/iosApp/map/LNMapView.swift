import MapCore
import composeApp
import UIKit

class IosMapViewDepsProviderImpl: IosMapViewDepsProvider {
    
    let viewController: UIViewController
    let controller: MapViewController
    let markerLayer: MapViewMarkerLayer
    
    init() {
        let mapViewController = UiMapViewController()
        let mapView = mapViewController.mapView
        self.viewController = mapViewController
        self.controller = MapViewControllerImpl(mapView: mapView)
        self.markerLayer = MapViewMarkerLayerImpl(mapView: mapView)
    }
}
