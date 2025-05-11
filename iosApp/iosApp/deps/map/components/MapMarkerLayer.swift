import composeApp
import UIKit
import MapLibre

class MapMarkerLayer : MapViewMarkerLayer {
    
    private let mapView: MLNMapView

    init(mapView: MLNMapView) {
        self.mapView = mapView
    }

    func addMarker(marker: MapViewMarker) {
        let annotation = MLNPointAnnotation()
        let coord = mapToCoordinate(geoPoint: marker.geoPoint)
        annotation.coordinate = coord
        annotation.title = marker.id
        
        mapView.removeAnnotations(getAnnotationsWith(title: marker.id))
        mapView.addAnnotation(annotation)
    }
    
    private func getAnnotationsWith(title: String) -> [any MLNAnnotation] {
        var annotations = mapView.annotations != nil ? mapView.annotations! : []
        annotations.removeAll(
            where: { annotation in
                let titleWTF = annotation.title?.self!.self
                let title = titleWTF!
                return !title.elementsEqual(title)
            }
        )
        return annotations
    }
}
