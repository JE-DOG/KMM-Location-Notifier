package notification

import ru.khinkal.locationNotifier.LocationNotifierApp
import ru.khinkal.locationNotifier.core.notification.LocationBroadcastNotificationChannelService

actual fun getNotificationService(): NotificationServiceVariants =
    AndroidNotificationServiceVariants()

class AndroidNotificationServiceVariants: NotificationServiceVariants {

    private val notificationChannelService = LocationBroadcastNotificationChannelService(
        context = LocationNotifierApp.INSTANCE,
    )

    init {
        notificationChannelService.init()
    }

    override fun sendNotification(
        id: Int,
        title: String,
        description: String,
        isSoundEnabled: Boolean
    ) {
        val notification = notificationChannelService.getNotificationBuilder {
            setOnlyAlertOnce(isSoundEnabled)
            setContentTitle(title)
            setContentText(description)
        }.build()

        notificationChannelService.notify(
            notificationId = id,
            notification = notification,
        )
    }
}