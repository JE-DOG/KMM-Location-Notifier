package kmp.core.location

import kmp.core.deps.SystemDeps
import kotlinx.coroutines.CoroutineScope
import ru.khinkal.locationNotifier.core.location.IosLocationService
import ru.khinkal.locationNotifier.core.location.LocationService

actual object LocationServiceFactory {

    actual fun createLocationService(
        systemDeps: SystemDeps,
        coroutineScope: CoroutineScope
    ): LocationService {
        return IosLocationService(
            coroutineScope = coroutineScope,
        )
    }
}
