import composeApp
import MapLibre
import MapKit
import UIKit

class MapViewProviderImpl : IosMapViewProvider {

    let viewController: UIViewController
    let controller: MapController
    let markerLayer: MapViewMarkerLayer
    let handler: MapViewHandler

    init() {
        let handler = MapViewHandler()
        let mapViewController = MapUiViewController()
        let mapView = mapViewController.mapView
        let markerLayer = MapMarkerLayer(mapView: mapView)

        self.handler = handler
        self.markerLayer = markerLayer
        self.viewController = mapViewController
        self.controller = MapControllerImpl(mapView: mapView)
        setupMap(mapViewController)
    }
    
    private func setupMap(_ mapViewController: MapUiViewController) {
        mapViewController.setOnClickListener(
            onClick: { geoPoint in
                self.handler.onClick(geoPoint: geoPoint)
            }
        )
    }
}
