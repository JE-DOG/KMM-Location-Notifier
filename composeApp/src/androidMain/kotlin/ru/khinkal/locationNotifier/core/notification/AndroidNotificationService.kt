package ru.khinkal.locationNotifier.core.notification

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import ru.khinkal.locationNotifier.AppActivity
import ru.khinkal.locationNotifier.R
import ru.khinkal.locationNotifier.core.notification.model.NotificationData

class AndroidNotificationService(
    private val context: Context
) : NotificationService {

    private val notificationManager by lazy {
        NotificationManagerCompat.from(context)
    }

    init {
        setupNotificationChannels()
    }

    private fun setupNotificationChannels() {
        val channelName = context.getString(R.string.base_channel_name)
        val channel = NotificationChannel(
            CHANNEL_ID,
            channelName,
            NotificationManager.IMPORTANCE_HIGH,
        )
            .apply {
                enableVibration(true)
            }
        notificationManager.createNotificationChannel(channel)
    }

    @SuppressLint("MissingPermission")
    override fun notify(notificationData: NotificationData) {
        val notification = notificationData.toNotificationBuilder(context).build()

        notify(notificationData.id, notification)
    }

    @SuppressLint("MissingPermission")
    fun notify(
        id: Int,
        notification: Notification,
    ) {
        notificationManager.notify(
            /* id = */ id,
            /* notification = */ notification,
        )
    }

    fun notification(
        title: String,
        builder: NotificationCompat.Builder.() -> Unit = {},
    ): NotificationCompat.Builder {
        return NotificationCompat.Builder(context, CHANNEL_ID)
            .apply(builder)
            .apply {
                setContentTitle(title)
                setSmallIcon(R.drawable.app_icon)
                setContentIntent(createAppLaunchPendingIntent())
            }
    }

    private fun createAppLaunchPendingIntent(): PendingIntent {
        return PendingIntent.getActivity(
            context,
            0,
            AppActivity.createIntent(context),
            PendingIntent.FLAG_IMMUTABLE,
        )
    }

    fun canUseFullScreenIntent(): Boolean {
        return notificationManager.canUseFullScreenIntent()
    }

    companion object {

        const val CHANNEL_ID = "ID"
    }
}
