package ru.khinkal.locationNotifier.core.notification.model

data class NotificationData(
    val id: Int,
    val title: String,
    val notifyType: NotificationNotifyType,
    val description: String? = null,
)
