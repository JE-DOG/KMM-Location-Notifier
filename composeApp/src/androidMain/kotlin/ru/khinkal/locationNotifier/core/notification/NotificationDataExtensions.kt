package ru.khinkal.locationNotifier.core.notification

import android.content.Context
import androidx.core.app.NotificationCompat
import ru.khinkal.locationNotifier.R
import ru.khinkal.locationNotifier.core.notification.model.NotificationData

fun NotificationData.toNotificationBuilder(context: Context): NotificationCompat.Builder {
    return NotificationCompat.Builder(context, AndroidNotificationService.CHANNEL_ID)
        .apply {
            setContentTitle(title)
            setSmallIcon(R.drawable.app_icon)
            setCategory(NotificationCompat.CATEGORY_STATUS)
            description?.let { setContentText(it) }
        }
}
