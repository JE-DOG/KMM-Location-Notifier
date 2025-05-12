import composeApp
import MapLibre
import UIKit

class MapUiViewController: UIViewController, MLNMapViewDelegate {
    
    private var onClickListener: ((GeoPoint) -> Void)?

    lazy var mapView: MLNMapView = MLNMapView()
    
    override func loadView() { view = mapView }

    override func viewDidLoad() {
        super.viewDidLoad()
        setupMap()
    }

    private func setupMap() {
        mapView.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        mapView.delegate = self
        setupMapProperties()
        setupMapListeners()
    }
    
    private func setupMapProperties() {
        let styleURL = URL(string: MapViewProperty.shared.STYLE_URL)
        mapView.styleURL = styleURL
        mapView.tileCacheEnabled = true
        mapView.logoView.isHidden = true
        mapView.compassView.isHidden = true
        mapView.allowsRotating = false
    }
    
    private func setupMapListeners() {
        let tap = UITapGestureRecognizer(
            target: self,
            action: #selector(handleTap(sender:))
        )
        for recognizer in mapView.gestureRecognizers! where recognizer is UITapGestureRecognizer {
            tap.require(toFail: recognizer)
        }
        mapView.addGestureRecognizer(tap)
    }

    @objc func handleTap(sender: UITapGestureRecognizer) {
        guard let mapView = sender.view as? MLNMapView else { return }
        let point = sender.location(in: mapView)
        let coord = mapView.convert(point, toCoordinateFrom: mapView)
        let geoPoint = mapToGeoPoint(coord: coord)
        onClickListener?(geoPoint)
    }

    func setOnClickListener(onClick: @escaping (GeoPoint) -> Void) {
        self.onClickListener = onClick
    }
}
