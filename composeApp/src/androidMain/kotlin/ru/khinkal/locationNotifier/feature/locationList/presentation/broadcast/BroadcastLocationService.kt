package ru.khinkal.locationNotifier.feature.locationList.presentation.broadcast

import android.app.Notification
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.khinkal.locationNotifier.core.ext.coroutines.launchCatching
import ru.khinkal.locationNotifier.core.location.LocationManagerImpl
import ru.khinkal.locationNotifier.core.location.model.BaseGeoPoint
import ru.khinkal.locationNotifier.core.ext.location.distanceInMeters
import ru.khinkal.locationNotifier.core.ext.location.seconds
import ru.khinkal.locationNotifier.core.notification.LocationBroadcastNotificationChannelService
import ru.khinkal.locationNotifier.core.notification.NotificationChannelService
import ru.khinkal.locationNotifier.core.utill.ext.isServiceActive
import ru.khinkal.locationNotifier.core.vibration.VibrationService
import ru.khinkal.locationNotifier.core.vibration.VibrationServiceImpl
import ru.khinkal.locationNotifier.feature.locationList.domain.model.GeoPoint
import ru.khinkal.locationNotifier.feature.settings.data.factory.SettingsManagerImplFactory
import ru.khinkal.locationNotifier.feature.settings.domain.SettingsManager

class BroadcastLocationService: Service() {

    private val locationManager by lazy { LocationManagerImpl(baseContext) }
    private val foregroundNotificationChannel: NotificationChannelService by lazy {
        LocationBroadcastNotificationChannelService(baseContext).apply {
            init()
        }
    }
    private val vibrationService: VibrationService by lazy {
        VibrationServiceImpl(baseContext)
    }
    private val lifecycleScope = CoroutineScope(Dispatchers.Main)
    private val settingsManager: SettingsManager = SettingsManagerImplFactory.create()

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val goalGeoPoint: GeoPoint = intent.goalLocation
        val foregroundNotification = foregroundNotificationChannel.getStartBroadcastNotification()

        startForeground(
            FOREGROUND_ACTIVE_NOTIFICATION_ID,
            foregroundNotification,
        )

        lifecycleScope.launchCatching(
            onFailure = {
                Toast.makeText(baseContext, "Something went wrong", Toast.LENGTH_SHORT)
                    .show()
                stopSelf()
            }
        ) {
            locationManager.broadcastLocation(
                updateSeconds = settingsManager.getLocationUpdateSeconds(),
            )
                .collect { geoPoint ->
                    val goalBaseGeoPoint = BaseGeoPoint(goalGeoPoint)
                    val metersToGoal = geoPoint distanceInMeters goalBaseGeoPoint

                    if (metersToGoal <= goalGeoPoint.meters){
                        actionOnGetToGoal(
                            goalName = goalGeoPoint.name,
                            vibrationState = settingsManager.isVibrationEnabled(),
                        )
                    }else {
                        actionOnProgress(
                            goalGeoPoint.name,
                            metersToGoal,
                        )
                    }
                }
        }

        return START_NOT_STICKY
    }

    private fun actionOnProgress(
        goalName: String,
        metersToGoal: Int,
    ) {
        val distanceToGoalNotification = foregroundNotificationChannel.getDistanceToGoalNotification(
            goalName = goalName,
            metersToGoal = metersToGoal
        )

        foregroundNotificationChannel.notify(
            notificationId = FOREGROUND_ACTIVE_NOTIFICATION_ID,
            notification = distanceToGoalNotification,
        )
    }

    private fun actionOnGetToGoal(
        goalName: String,
        vibrationState: Boolean,
    ) {
        val getToLocationNotification = foregroundNotificationChannel.getGetToLocationNotification(
            goalName = goalName,
        )

        foregroundNotificationChannel.notify(
            notificationId = GET_TO_LOCATION_NOTIFICATION_ID,
            notification = getToLocationNotification,
        )
        if (vibrationState) {
            vibrationService.vibrate(1.seconds)
        }
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleScope.cancel()
    }

    private fun NotificationChannelService.getGetToLocationNotification(
        goalName: String,
    ): Notification {
        val notification = getNotificationBuilder {
            setContentTitle(goalName)
        }
            .build()

        return notification
    }

    private fun NotificationChannelService.getStartBroadcastNotification(): Notification {
        return getNotificationBuilder {
            val title = "Broadcast location start"

            setOngoing(true)
            setOnlyAlertOnce(true)
            setContentTitle(title)
        }
            .build()
    }

    private fun NotificationChannelService.getDistanceToGoalNotification(
        goalName: String,
        metersToGoal: Int,
    ): Notification {
        val notificationTitle = "Distance to the $goalName"
        val notificationDescription = "$metersToGoal meters"

        val notification = getNotificationBuilder {
            setContentTitle(notificationTitle)
            setContentText(notificationDescription)
            setOngoing(true)
            setOnlyAlertOnce(true)
        }
            .build()

        return notification
    }

    private val Intent.goalLocation: GeoPoint get() {
        val geoPointJson = getStringExtra(GOAL_GEO_POINT_KEY)!!
        val geoPoint = Json.decodeFromString<GeoPoint>(geoPointJson)
        return geoPoint
    }

    companion object {
        private const val FOREGROUND_ACTIVE_NOTIFICATION_ID = 2
        private const val GET_TO_LOCATION_NOTIFICATION_ID = 1

        private const val GOAL_GEO_POINT_KEY = "GOAL_GEO_POINT_KEY"

        /**
         * @param geoPoint Точка до которой будет активен слушатель
         * @return true - Нет активного сервиса и был создан новый, false - Есть активный сервис, новый сервис не создан
         */
        fun startBroadcast(
            context: Context,
            geoPoint: GeoPoint,
        ): Boolean {
            val geoPointJson = Json.encodeToString(geoPoint)
            if (context.isServiceActive(BroadcastLocationService::class.java)) return false
            val intent = Intent(context, BroadcastLocationService::class.java).apply {
                putExtra(GOAL_GEO_POINT_KEY, geoPointJson)
            }

            context.startService(intent)
            return true
        }
    }
}