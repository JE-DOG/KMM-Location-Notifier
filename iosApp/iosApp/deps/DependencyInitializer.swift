import composeApp

class DependencyInitializer {
    
    func initDeps() {
        IosMapViewDepsProviderCompanion.shared.INSTANCE = IosMapViewDepsProviderFactoryImpl()
    }
}
