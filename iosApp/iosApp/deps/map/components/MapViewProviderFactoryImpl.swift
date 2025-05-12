import composeApp

class MapViewProviderFactoryImpl : IosMapViewProviderFactory {

    func create() -> any IosMapViewProvider {
        return MapViewProviderImpl()
    }
}
