import UIKit
import composeApp
import CoreLocation

@main
class AppDelegate: UIResponder, UIApplicationDelegate {
    var window: UIWindow?
    var locationManager: CLLocationManager?
    
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        guard let coordinate = locations.last?.coordinate else { return }
        print("Latitude: \(coordinate.latitude), Longitude: \(coordinate.longitude)")
    }
    
    func locationManager(_ manager: CLLocationManager, didFailWithError error: any Error) {
        // TODO AFTERR
    }

    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?
    ) -> Bool {
        window = UIWindow(frame: UIScreen.main.bounds)
        locationManager = createLocationManager()
        if let window = window {
            let mainViewController = MainKt.MainViewController()
            window.rootViewController = mainViewController
            window.makeKeyAndVisible()
            requestLocationPermission()
        }
        return true
    }
    
    private func requestLocationPermission() {
        let authorizationStatus = locationManager?.authorizationStatus
        if locationManager?.authorizationStatus == CLAuthorizationStatus.authorizedWhenInUse {
            locationManager?.requestAlwaysAuthorization()
        } else if authorizationStatus != CLAuthorizationStatus.authorizedAlways {
            locationManager?.requestWhenInUseAuthorization()
        }
    }

    private func createLocationManager() -> CLLocationManager {
        let locationManager = CLLocationManager()
        locationManager.delegate = self
        locationManager.allowsBackgroundLocationUpdates = true
        locationManager.showsBackgroundLocationIndicator = true
        return locationManager
    }
}

extension AppDelegate: CLLocationManagerDelegate {
    func locationManagerDidChangeAuthorization(_ manager: CLLocationManager) {
        switch manager.authorizationStatus {
        case.authorizedAlways:
            print("When user did not yet determined")
            requestLocationUpdate()
        case .notDetermined:
            print("When user did not yet determined")
        case .restricted:
            print("Restricted by parental control")
        case .denied:
            print("When user select option Dont't Allow")
        case .authorizedWhenInUse:
            manager.requestAlwaysAuthorization()
            print("When user select option Allow While Using App or Allow Once")
        default:
            print("default")
        }
    }
    
    private func requestLocationUpdate() {
        locationManager?.requestLocation()
    }
}
