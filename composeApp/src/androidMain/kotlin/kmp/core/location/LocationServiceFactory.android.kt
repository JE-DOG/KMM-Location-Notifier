package kmp.core.location

import kmp.core.asAndroid
import kmp.core.deps.SystemDeps
import kotlinx.coroutines.CoroutineScope
import ru.khinkal.locationNotifier.core.location.AndroidLocationService
import ru.khinkal.locationNotifier.core.location.LocationService

actual object LocationServiceFactory {

    actual fun createLocationService(
        systemDeps: SystemDeps,
        coroutineScope: CoroutineScope
    ): LocationService {
        return AndroidLocationService(
            context = systemDeps.asAndroid().applicationContext,
            coroutineScope = coroutineScope,
        )
    }
}
