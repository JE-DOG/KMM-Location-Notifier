import composeApp
import MapCore

class MapViewControllerImpl: MapViewController {
    
    let mapView: MCMapView
    
    init(mapView: MCMapView) {
        self.mapView = mapView
    }
    
    func getZoom() -> Double {
        mapView.camera.getZoom()
    }
    
    func setMaxZoom(zoom: Double) {
        mapView.camera.setMaxZoom(zoom)
    }
    
    func setMinZoom(zoom: Double) {
        mapView.camera.setMinZoom(zoom)
    }
    
    func setCenter(baseGeoPoint: BaseGeoPoint, zoom: Double) {
        move(baseGeoPoint: baseGeoPoint, zoom: zoom, animated: false)
    }
    
    func moveTo(baseGeoPoint: BaseGeoPoint, zoom: Double) {
        move(baseGeoPoint: baseGeoPoint, zoom: zoom, animated: true)
    }
    
    func zoomTo(zoom: Double) {
        mapView.camera.setZoom(zoom, animated: true)
    }
    
    private func move(baseGeoPoint: BaseGeoPoint, zoom: Double, animated: Bool) {
        let coordinate = mapToCoordinate(baseGeoPoint: baseGeoPoint)
        let camera = mapView.camera
        camera.move(toCenterPositionZoom: coordinate, zoom: zoom, animated: animated)
    }
    
}

func mapToCoordinate(baseGeoPoint: BaseGeoPoint) -> MCCoord {
    return MCCoord(lat: baseGeoPoint.latitude, lon: baseGeoPoint.longitude)
}
