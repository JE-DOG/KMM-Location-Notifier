package kmp.core.vibration

import kmp.core.deps.SystemDeps
import ru.khinkal.locationNotifier.core.vibration.VibrationService

actual object VibrationServiceFactory {

    actual fun create(systemDeps: SystemDeps): VibrationService {
        return IosVibrationService()
    }
}
