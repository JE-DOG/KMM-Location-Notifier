package kmp.core.notification

import kmp.core.asAndroid
import kmp.core.deps.SystemDeps
import ru.khinkal.locationNotifier.core.notification.AndroidNotificationService
import ru.khinkal.locationNotifier.core.notification.NotificationService

actual object NotificationServiceFactory {

    actual fun createNotificationService(
        systemDeps: SystemDeps,
    ): NotificationService {
        return AndroidNotificationService(
            context = systemDeps.asAndroid().applicationContext,
        )
    }
}
