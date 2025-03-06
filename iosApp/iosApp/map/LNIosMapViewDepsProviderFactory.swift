import composeApp

class IosMapViewDepsProviderFactoryImpl: IosMapViewDepsProviderFactory {
    
    func create() -> any IosMapViewDepsProvider {
        return IosMapViewDepsProviderImpl()
    }
}
