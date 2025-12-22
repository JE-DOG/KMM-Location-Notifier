package ru.khinkal.locationNotifier.core.notification

import platform.UserNotifications.UNNotification
import platform.UserNotifications.UNNotificationPresentationOptionBanner
import platform.UserNotifications.UNNotificationPresentationOptionList
import platform.UserNotifications.UNNotificationPresentationOptionSound
import platform.UserNotifications.UNNotificationPresentationOptions
import platform.UserNotifications.UNNotificationResponse
import platform.UserNotifications.UNUserNotificationCenter
import platform.UserNotifications.UNUserNotificationCenterDelegateProtocol
import platform.darwin.NSObject

class NotificationDelegate : UNUserNotificationCenterDelegateProtocol, NSObject() {

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
        val hasSound = willPresentNotification.request.content.sound != null
        val options = if (!hasSound) {
            UNNotificationPresentationOptionList
        } else {
            UNNotificationPresentationOptionSound or
                    UNNotificationPresentationOptionBanner or
                    UNNotificationPresentationOptionList
        }
        withCompletionHandler(options)
    }
}
