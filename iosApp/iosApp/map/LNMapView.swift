import MapCore
import composeApp
import UIKit

class IosMapViewDepsProviderImpl: IosMapViewDepsProvider {
    
    let viewController: UIViewController
    let controller: MapViewController
    let markerLayer: MapViewMarkerLayer
    let handler: MapViewHandler
    
    init() {
        let mapViewListener = MapViewListener()
        let iconLayer = MapViewMarkerLayerImpl()
        let handler = MapViewHandler()
        let mapViewController = UiMapViewController(mapViewClickListener: mapViewListener, iconLayer: iconLayer)
        let mapView = mapViewController.mapView
        mapViewListener.setOnClickListener(
            onClick: { ccord in
                let baseGeoPoint = mapToBaseGeoPoint(coord: ccord)
                handler.onClick(geoPoint: baseGeoPoint)
            }
        )
        
        self.handler = handler
        self.markerLayer = iconLayer
        self.viewController = mapViewController
        self.controller = MapViewControllerImpl(mapView: mapView)
    }
}
