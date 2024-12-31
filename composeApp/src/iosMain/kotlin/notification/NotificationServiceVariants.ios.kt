package notification

actual fun getNotificationService(): NotificationServiceVariants =
    IosNotificationServiceVariants()

class IosNotificationServiceVariants : NotificationServiceVariants {

    private val notificationManager = NotificationManager()

    override fun sendNotification(
        id: Int,
        title: String,
        description: String,
        isSoundEnabled: Boolean
    ) {
        notificationManager.sendNotification(
            id = id,
            title = title,
            description = description,
            isSoundEnabled = isSoundEnabled,
        )
    }
}