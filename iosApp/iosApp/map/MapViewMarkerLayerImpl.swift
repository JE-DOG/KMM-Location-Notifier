import MapCore
import composeApp
import UIKit

class MapViewMarkerLayerImpl: MapViewMarkerLayer {
    
    let mapView: MCMapView
    let iconLayer = MCIconLayerInterface.create()
    
    init(mapView: MCMapView) {
        self.mapView = mapView
        mapView.add(layer: iconLayer?.asLayerInterface())
    }
    
    func addMarker(marker: MapViewMarker) {
        let image = UIImage(resource: ImageResource.icMyLocation)
        let texture = try! TextureHolder(image.cgImage!)
        let icon = MCIconFactory.createIcon(
            marker.id,
            coordinate: mapToCoordinate(baseGeoPoint: marker.baseGeoPoint),
            texture: texture,
            iconSize: .init(x: Float(texture.getImageWidth()), y: Float(texture.getImageHeight())),
            scale: .FIXED,
            blendMode: .NORMAL
        )
    }
}
