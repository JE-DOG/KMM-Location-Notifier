package ru.khinkal.locationNotifier.core.notification

import platform.UIKit.UIApplication
import platform.UIKit.registerForRemoteNotifications
import platform.UserNotifications.UNAuthorizationOptionAlert
import platform.UserNotifications.UNAuthorizationOptionBadge
import platform.UserNotifications.UNAuthorizationOptionSound
import platform.UserNotifications.UNAuthorizationStatusNotDetermined
import platform.UserNotifications.UNMutableNotificationContent
import platform.UserNotifications.UNNotificationInterruptionLevel
import platform.UserNotifications.UNNotificationRequest
import platform.UserNotifications.UNNotificationSound
import platform.UserNotifications.UNUserNotificationCenter
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import ru.khinkal.locationNotifier.core.notification.model.NotificationData
import ru.khinkal.locationNotifier.core.notification.model.NotificationNotifyType

class IosNotificationService : NotificationService {

    private val notificationCenter =
        UNUserNotificationCenter.currentNotificationCenter()

    init {
        notificationCenter.setDelegate(NotificationDelegate())
        requestPermission()
    }

    private fun requestPermission() {
        notificationCenter.getNotificationSettingsWithCompletionHandler { notificationSettings ->
            if (notificationSettings == null) return@getNotificationSettingsWithCompletionHandler
            when (notificationSettings.authorizationStatus) {
                UNAuthorizationStatusNotDetermined -> {
                    notificationCenter.requestAuthorizationWithOptions(
                        options = UNAuthorizationOptionAlert or
                                UNAuthorizationOptionSound or
                                UNAuthorizationOptionBadge,
                    ) { granted, _ ->
                        if (granted) {
                            dispatch_async(dispatch_get_main_queue()) {
                                UIApplication.sharedApplication.registerForRemoteNotifications()
                            }
                        }
                    }
                }
            }
        }
    }

    override fun notify(notificationData: NotificationData) {
        sendNotification(notificationData)
    }

    private fun sendNotification(notificationData: NotificationData) {
        dispatch_async(dispatch_get_main_queue()) {
            val notificationId = notificationData.id.toString()
            val content = createNotificationContent(notificationData)
            val request = UNNotificationRequest.requestWithIdentifier(
                identifier = notificationId,
                content = content,
                trigger = null,
            )

            notificationCenter.addNotificationRequest(request) { error ->
                if (error != null) {
                    println("Error sending notification: ${error.localizedDescription}")
                }
            }
        }
    }

    private fun createNotificationContent(notificationData: NotificationData): UNMutableNotificationContent {
        return UNMutableNotificationContent().apply {
            setTitle(notificationData.title)
            notificationData.description?.let { setBody(it) }
            setThreadIdentifier(notificationData.id.toString())
            setNotifyType(notificationData.notifyType)
        }
    }

    private fun UNMutableNotificationContent.setNotifyType(
        notifyType: NotificationNotifyType,
    ): UNMutableNotificationContent {
        when (notifyType) {
            NotificationNotifyType.Silent -> {
                setInterruptionLevel(
                    UNNotificationInterruptionLevel.UNNotificationInterruptionLevelPassive,
                )
            }

            is NotificationNotifyType.Sound -> {
                setSound(UNNotificationSound.defaultSound)
                setInterruptionLevel(
                    UNNotificationInterruptionLevel.UNNotificationInterruptionLevelActive,
                )
            }
        }
        return this
    }
}
