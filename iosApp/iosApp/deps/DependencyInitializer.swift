import composeApp

class DependencyInitializer {
    
    func initDeps() {
        let initializer = DiInitializer()
        initializer.invoke()
//        IosMapViewDepsProviderCompanion.shared.INSTANCE = IosMapViewDepsProviderFactoryImpl()
    }
}
