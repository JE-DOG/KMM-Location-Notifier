package ru.khinkal.locationNotifier.core.notification.model

sealed interface NotificationNotifyType {

    data object Sound : NotificationNotifyType

    data object Silent : NotificationNotifyType
}
