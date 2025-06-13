package kmp.core.notification

import kmp.core.deps.SystemDeps
import ru.khinkal.locationNotifier.core.notification.NotificationService

expect object NotificationServiceFactory {

    fun createNotificationService(
        systemDeps: SystemDeps,
    ): NotificationService
}
