package ru.khinkal.locationNotifier.core.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

class LocationBroadcastNotificationChannelService(
    context: Context
): NotificationChannelService(context) {

    override val channel: NotificationChannel = NotificationChannel(
        CHANNEL_ID,
        NAME,
        NotificationManager.IMPORTANCE_HIGH,
    )

    companion object {

        const val CHANNEL_ID = "FOREGROUND_NOTIFICATION_ID"
        const val NAME = "Location notifier"

    }
}