import MapCore
import composeApp
import UIKit

class UiMapViewController: UIViewController {
    
    lazy var mapView = MCMapView()
    let mapViewClickListener = MapViewClickListener()
    let mapProperty = MapViewProperty.shared
    
    override func loadView() { view = mapView }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupCamera()
        setupTileOverlay()
    }

    private func setupTileOverlay() {
        let tileOverlay = TiledRasterLayer(webMercatorUrlFormat: mapProperty.TILE_OVERLAY)
        mapView.add(layer: tileOverlay)
        tileOverlay.tiledLayerInterface.setCallbackHandler(mapViewClickListener)
    }

    private func setupCamera() {
        mapView.camera.setBoundsRestrictWholeVisibleRect(true)
    }
}

class MapViewClickListener: MCTiled2dMapRasterLayerCallbackInterface {
    
    private var onClickListener: ((MCCoord) -> Void)? = nil
    
    func setOnClickListener(onClick: @escaping (MCCoord) -> Void) {
        self.onClickListener = onClick
    }
    
    func onClickConfirmed(_ coord: MCCoord) -> Bool {
        onClickListener?.self(coord)
        return true
    }
    
    func onLongPress(_ coord: MCCoord) -> Bool {
        return true
    }
}
