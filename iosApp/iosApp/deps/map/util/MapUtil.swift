import composeApp
import MapLibre

func mapToGeoPoint(coord: CLLocationCoordinate2D) -> GeoPoint {
    let coordinate = coord
    return GeoPoint(
        latitude: coordinate.latitude,
        longitude: coordinate.longitude
    )
}

func mapToCoordinate(geoPoint: GeoPoint) -> CLLocationCoordinate2D {
    return CLLocationCoordinate2D(
        latitude: geoPoint.latitude,
        longitude: geoPoint.longitude
    )
}
