package notification

expect fun getNotificationService(): NotificationServiceVariants

interface NotificationServiceVariants {

    fun sendNotification(
        id: Int,
        title: String,
        description: String,
        isSoundEnabled: Boolean,
    )
}