import MapCore
import composeApp
import UIKit

class MapViewMarkerLayerImpl: MapViewMarkerLayer, MapViewLayer {
    
    var iconLayer: MCIconLayerInterface? = nil
    
    func setup(mapView: MCMapView) {
        let iconLayer = MCIconLayerInterface.create()
        self.iconLayer = iconLayer
        mapView.add(layer: iconLayer?.asLayerInterface())
    }
    
    func addMarker(marker: MapViewMarker) {
        let image = UIImage(resource: ImageResource.icMyLocation)
        let texture = try! TextureHolder(image.cgImage!)
        let imageSize = image.size
        let icon = MCIconFactory.createIcon(
            withAnchor: marker.id,
            coordinate: mapToCoordinate(baseGeoPoint: marker.baseGeoPoint),
            texture: texture,
            iconSize: .init(x: Float(imageSize.width / 2), y: Float(imageSize.height / 2)),
            scale: .SCALE_INVARIANT,
            blendMode: .NORMAL,
            iconAnchor: MCVec2F(x: 0.5, y: 1)
        )
        
        iconLayer?.removeIdentifier(marker.id)
        iconLayer?.add(icon)
    }
}
