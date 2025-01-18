import composeApp

class DependencyInitializer {
    
    let mapDepsProvider = IosMapViewDepsProviderImpl()
    
    func initDeps() {
        IosMapViewDepsProviderCompanion.shared.INSTANCE = mapDepsProvider
    }
}
