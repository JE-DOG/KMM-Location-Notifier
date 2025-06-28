package ru.khinkal.locationNotifier.feature.main.presentation.broadcast

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.NotificationCompat
import kmp.core.AndroidSystemDeps
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.json.Json
import ru.khinkal.locationNotifier.core.ext.location.distanceInMetersTo
import ru.khinkal.locationNotifier.core.location.LocationService
import ru.khinkal.locationNotifier.core.notification.AndroidNotificationService
import ru.khinkal.locationNotifier.core.notification.model.NotificationData
import ru.khinkal.locationNotifier.core.notification.model.NotificationNotifyType
import ru.khinkal.locationNotifier.core.utill.ext.cancelServie
import ru.khinkal.locationNotifier.core.utill.ext.isServiceActive
import ru.khinkal.locationNotifier.core.vibration.VibrationService
import ru.khinkal.locationNotifier.feature.goalBroadcaster.di.GoalGeoPointBroadcasterComponent
import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint
import ru.khinkal.locationNotifier.feature.settings.domain.SettingsManager

class GoalGeoPointBroadcastForegroundService : Service() {

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    private lateinit var locationService: LocationService
    private lateinit var notificationService: AndroidNotificationService
    private lateinit var settingsManager: SettingsManager
    private lateinit var vibrationService: VibrationService

    override fun onCreate() {
        val component = GoalGeoPointBroadcasterComponent(
            systemDeps = AndroidSystemDeps(baseContext),
            coroutineScope = coroutineScope,
        )
        locationService = component.locationService
        notificationService = component.notificationService as AndroidNotificationService
        vibrationService = component.vibrationService
        settingsManager = component.settingsManager
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val goalGeoPoint: GoalGeoPoint = intent.goalGeoPoint
        val foregroundNotificationBuilder = goalGeoPoint.createForegroundNotificationBuilder()

        startForeground(
            FOREGROUND_ACTIVE_NOTIFICATION_ID,
            foregroundNotificationBuilder.build(),
        )
        observeLocation(
            goalGeoPoint = goalGeoPoint,
            foregroundNotificationBuilder = foregroundNotificationBuilder,
        )

        return START_REDELIVER_INTENT
    }

    private val Intent.goalGeoPoint: GoalGeoPoint
        get() {
            val geoPointJson = getStringExtra(GOAL_GEO_POINT_KEY)!!
            val goalGeoPoint = Json.decodeFromString<GoalGeoPoint>(geoPointJson)
            return goalGeoPoint
        }

    private fun GoalGeoPoint.createForegroundNotificationBuilder(): NotificationCompat.Builder {
        return notificationService.notification(name) {
            setOngoing(true)
            setOnlyAlertOnce(true)
        }
    }

    private fun observeLocation(
        goalGeoPoint: GoalGeoPoint,
        foregroundNotificationBuilder: NotificationCompat.Builder,
    ) {
        locationService.geoPoint
            .filterNotNull()
            .onEach { geoPoint ->
                val metersToGoal = geoPoint distanceInMetersTo goalGeoPoint.geoPoint

                if (metersToGoal <= goalGeoPoint.meters) {
                    onReachGoal(
                        goalGeoPoint = goalGeoPoint,
                        notifyEnabled = settingsManager.isNotifyEnabled(),
                    )
                } else {
                    onProgressToReachGoal(
                        metersToGoal = metersToGoal,
                        foregroundNotificationBuilder = foregroundNotificationBuilder,
                    )
                }
            }
            .catch {
                // TODO Make better user notification about error
                Toast.makeText(baseContext, "Something went wrong", Toast.LENGTH_SHORT)
                    .show()
                stopSelf()
            }
            .launchIn(coroutineScope)
    }

    private fun onReachGoal(
        notifyEnabled: Boolean,
        goalGeoPoint: GoalGeoPoint,
    ) {
        val notificationData = goalGeoPoint.createReachGoalGeoPointNotificationData(
            notifyEnabled = notifyEnabled,
        )
        notificationService.notify(notificationData)
        if (notifyEnabled) {
            vibrationService.vibrate()
        }
        stopSelf()
    }

    private fun GoalGeoPoint.createReachGoalGeoPointNotificationData(
        notifyEnabled: Boolean,
    ): NotificationData {
        return NotificationData(
            id = FOREGROUND_FINISH_NOTIFICATION_ID,
            notifyType =
                if (notifyEnabled) NotificationNotifyType.Sound
                else NotificationNotifyType.Silent,
            title = "Вы добрались до $name",
        )
    }

    private fun onProgressToReachGoal(
        metersToGoal: Int,
        foregroundNotificationBuilder: NotificationCompat.Builder,
    ) {
        val notification = foregroundNotificationBuilder
            .setOnProgressToReachGoal(metersToGoal)
            .build()

        notificationService.notify(
            id = FOREGROUND_ACTIVE_NOTIFICATION_ID,
            notification = notification,
        )
    }

    private fun NotificationCompat.Builder.setOnProgressToReachGoal(
        metersToGoal: Int,
    ): NotificationCompat.Builder {
        return apply {
            val description = getDescription(metersToGoal)
            setContentText(description)
        }
    }

    private fun getDescription(metersToGoal: Int): String {
        return "$metersToGoal meters"
    }

    override fun onDestroy() {
        coroutineScope.cancel()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    companion object {

        private const val FOREGROUND_ACTIVE_NOTIFICATION_ID = 2
        private const val FOREGROUND_FINISH_NOTIFICATION_ID = 3
        private const val GOAL_GEO_POINT_KEY = "GOAL_GEO_POINT_KEY"

        /**
         * Starts the service to listen for location updates until the destination point is reached.
         *
         * Note: The service will start only if there are no other active instances of this service.
         * @param goalGeoPoint The point to which the listener will remain active
         * @param context Required to start the service
         */
        fun start(
            context: Context,
            goalGeoPoint: GoalGeoPoint,
        ) {
            if (context.isServiceActive<GoalGeoPointBroadcastForegroundService>()) {
                context.cancelServie<GoalGeoPointBroadcastForegroundService>()
                return
            }
            val geoPointJson = Json.encodeToString(goalGeoPoint)
            val intent =
                Intent(context, GoalGeoPointBroadcastForegroundService::class.java).apply {
                    putExtra(GOAL_GEO_POINT_KEY, geoPointJson)
                }

            context.startService(intent)
        }
    }
}
