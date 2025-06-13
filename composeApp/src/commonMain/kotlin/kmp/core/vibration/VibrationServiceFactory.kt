package kmp.core.vibration

import kmp.core.deps.SystemDeps
import ru.khinkal.locationNotifier.core.vibration.VibrationService

expect object VibrationServiceFactory {

    fun create(systemDeps: SystemDeps) : VibrationService
}
