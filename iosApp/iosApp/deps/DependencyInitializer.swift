import composeApp

class DependencyInitializer {

    func invoke() {
        let initializer = DiInitializer()
        initializer.invoke()
        provideMapViewProvider()
    }

    private func provideMapViewProvider() {
        IosMapViewProviderFactoryCompanion.shared.INSTANCE = MapViewProviderFactoryImpl()
    }
}
