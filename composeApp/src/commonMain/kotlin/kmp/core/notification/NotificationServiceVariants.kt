package kmp.core.notification

expect fun getNotificationService(): NotificationServiceVariants

// TODO: LN-5 Rebuild broadcast and notification(Tech-debt iteration 4)
interface NotificationServiceVariants {

    fun sendNotification(
        id: Int,
        title: String,
        description: String = "",
        isSoundEnabled: Boolean = false,
    )
}
