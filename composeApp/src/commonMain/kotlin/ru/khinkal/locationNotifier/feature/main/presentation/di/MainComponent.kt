package ru.khinkal.locationNotifier.feature.main.presentation.di

import ru.khinkal.locationNotifier.feature.main.data.di.LocationListDataModule
import ru.khinkal.locationNotifier.feature.main.domain.LocationRepository
import ru.khinkal.locationNotifier.feature.main.presentation.di.deps.MainDeps
import ru.khinkal.locationNotifier.feature.main.presentation.di.deps.MainDepsFactory

class MainComponent(
    private val deps: MainDeps = MainDepsFactory.INSTANCE.create(),
) {

    private val locationListDataModule: LocationListDataModule by lazy {
        LocationListDataModule(
            systemDeps = deps.systemDeps,
            pathManager = deps.pathManager,
        )
    }

    val locationListRepository: LocationRepository by lazy {
        locationListDataModule.provideLocationRepository()
    }
}
