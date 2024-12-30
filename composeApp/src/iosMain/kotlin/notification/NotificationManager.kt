package notification

import platform.UIKit.UIApplication
import platform.UIKit.registerForRemoteNotifications
import platform.UserNotifications.UNAuthorizationOptionAlert
import platform.UserNotifications.UNAuthorizationOptionBadge
import platform.UserNotifications.UNAuthorizationOptionSound
import platform.UserNotifications.UNAuthorizationStatusNotDetermined
import platform.UserNotifications.UNMutableNotificationContent
import platform.UserNotifications.UNNotification
import platform.UserNotifications.UNNotificationPresentationOptions
import platform.UserNotifications.UNNotificationRequest
import platform.UserNotifications.UNNotificationResponse
import platform.UserNotifications.UNNotificationSound
import platform.UserNotifications.UNUserNotificationCenter
import platform.UserNotifications.UNUserNotificationCenterDelegateProtocol
import platform.darwin.NSObject
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue

class NotificationManager {

    private val notificationCenter =
        UNUserNotificationCenter.currentNotificationCenter()

    private val delegate = NotificationDelegate()

    init {
        notificationCenter.setDelegate(delegate)
    }

    fun requestPermission() {
        notificationCenter.getNotificationSettingsWithCompletionHandler { notificationSettings ->
            if (notificationSettings == null) return@getNotificationSettingsWithCompletionHandler
            when (notificationSettings.authorizationStatus) {
                UNAuthorizationStatusNotDetermined -> {
                    notificationCenter.requestAuthorizationWithOptions(
                        options = UNAuthorizationOptionAlert or
                                UNAuthorizationOptionSound or
                                UNAuthorizationOptionBadge,
                    ) { granted, error ->
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

    fun sendNotification(
        id: Int = 0,
        title: String,
        description: String,
        isSoundEnabled: Boolean = true,
    ) {
        val id = id.toString()
        dispatch_async(dispatch_get_main_queue()) {
            notificationCenter.removePendingNotificationRequestsWithIdentifiers(
                listOf(id)
            )
            val content = UNMutableNotificationContent().apply {
                setTitle(title)
                setBody(description)
                if (isSoundEnabled) {
                    setSound(UNNotificationSound.defaultSound)
                }
            }

            val request = UNNotificationRequest.requestWithIdentifier(
                identifier = id,
                content = content,
                trigger = null,
            )

            notificationCenter.addNotificationRequest(request) { error ->
                if (error == null) return@addNotificationRequest
                println("Error sending notification: ${error.localizedDescription}")
            }
        }
    }

    internal class NotificationDelegate : UNUserNotificationCenterDelegateProtocol, NSObject() {
        override fun userNotificationCenter(
            center: UNUserNotificationCenter,
            didReceiveNotificationResponse: UNNotificationResponse,
            withCompletionHandler: () -> Unit,
        ) {
            withCompletionHandler()
        }

        override fun userNotificationCenter(
            center: UNUserNotificationCenter,
            willPresentNotification: UNNotification,
            withCompletionHandler: (UNNotificationPresentationOptions) -> Unit,
        ) {
            withCompletionHandler(
                UNAuthorizationOptionAlert or
                        UNAuthorizationOptionSound or
                        UNAuthorizationOptionBadge,
            )
        }
    }
}
