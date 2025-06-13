package ru.khinkal.locationNotifier.core.vibration.di

import kmp.core.deps.SystemDeps
import kmp.core.vibration.VibrationServiceFactory
import ru.khinkal.locationNotifier.core.vibration.VibrationService

class VibrationServiceModule {

    fun provideVibrationService(
        systemDeps: SystemDeps,
    ): VibrationService {
        return VibrationServiceFactory.create(
            systemDeps = systemDeps,
        )
    }
}
