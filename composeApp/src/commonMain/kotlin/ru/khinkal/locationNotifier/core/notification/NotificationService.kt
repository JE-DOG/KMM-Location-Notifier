package ru.khinkal.locationNotifier.core.notification

import ru.khinkal.locationNotifier.core.notification.model.NotificationData

interface NotificationService {

    fun notify(notificationData: NotificationData)
}
