package ru.khinkal.locationNotifier.core.notification.di

import kmp.core.deps.SystemDeps
import kmp.core.notification.NotificationServiceFactory
import ru.khinkal.locationNotifier.core.notification.NotificationService

class NotificationServiceModule {

    fun provideNotificationService(
        systemDeps: SystemDeps,
    ): NotificationService {
        return NotificationServiceFactory.createNotificationService(
            systemDeps = systemDeps,
        )
    }
}
