import composeApp
import MapLibre

class MapControllerImpl : MapController {
    
    let mapView: MLNMapView
    
    init(mapView: MLNMapView) {
        self.mapView = mapView
    }
    
    func getZoom() -> Double {
        return mapView.zoomLevel
    }
    
    func setMaxZoom(zoom: Double) {
        mapView.maximumZoomLevel = zoom
    }
    
    func setMinZoom(zoom: Double) {
        mapView.minimumZoomLevel = zoom
    }
    
    func zoomTo(zoom: Double) {
        mapView.setZoomLevel(zoom, animated: true)
    }
    
    func setCenter(geoPoint: GeoPoint, zoom: Double) {
        let coord = mapToCoordinate(geoPoint: geoPoint)
        mapView.setCenter(coord, zoomLevel: zoom, animated: false)
    }
    
    func moveTo(geoPoint: GeoPoint, zoom: Double) {
        let coord = mapToCoordinate(geoPoint: geoPoint)
        mapView.setCenter(coord, zoomLevel: zoom, animated: true)
    }
}
