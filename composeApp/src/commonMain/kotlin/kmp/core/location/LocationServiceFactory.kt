package kmp.core.location

import kmp.core.deps.SystemDeps
import kotlinx.coroutines.CoroutineScope
import ru.khinkal.locationNotifier.core.location.LocationService

expect object LocationServiceFactory {

    fun createLocationService(
        systemDeps: SystemDeps,
        coroutineScope: CoroutineScope,
    ): LocationService
}
