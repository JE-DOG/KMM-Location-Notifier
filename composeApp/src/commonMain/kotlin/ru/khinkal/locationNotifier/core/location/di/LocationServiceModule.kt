package ru.khinkal.locationNotifier.core.location.di;

import kmp.core.deps.SystemDeps
import kmp.core.location.LocationServiceFactory
import kotlinx.coroutines.CoroutineScope
import ru.khinkal.locationNotifier.core.location.LocationService

class LocationServiceModule {

    fun provideLocationService(
        systemDeps: SystemDeps,
        coroutineScope: CoroutineScope,
    ): LocationService {
        return LocationServiceFactory.createLocationService(
            systemDeps = systemDeps,
            coroutineScope = coroutineScope,
        )
    }
}
