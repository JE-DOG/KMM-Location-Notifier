import MapCore
import composeApp
import UIKit

class UiMapViewController: UIViewController {
    
    lazy var mapView = MCMapView()
    let mapViewClickListener: MapViewListener
    let mapProperty = MapViewProperty.shared
    let iconLayer: MapViewLayer

    init(mapViewClickListener: MapViewListener, iconLayer: MapViewLayer) {
        self.mapViewClickListener = mapViewClickListener
        self.iconLayer = iconLayer
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func loadView() { view = mapView }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupCamera()
        setupTileOverlay()
        setupIconLayer()
    }

    private func setupTileOverlay() {
        let tileOverlay = TiledRasterLayer(webMercatorUrlFormat: mapProperty.TILE_OVERLAY)
        mapView.add(layer: tileOverlay)
        tileOverlay.tiledLayerInterface.setCallbackHandler(mapViewClickListener)
    }
    
    private func setupIconLayer() {
        iconLayer.setup(mapView: mapView)
    }

    private func setupCamera() {
        mapView.camera.setBoundsRestrictWholeVisibleRect(true)
    }
}

class MapViewListener: MCTiled2dMapRasterLayerCallbackInterface {
    
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
