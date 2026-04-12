package ru.khinkal.locationNotifier.feature.main.presentation.broadcast

import android.R.attr.name
import android.annotation.SuppressLint
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import kmp.core.AndroidSystemDeps
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.json.Json
import ru.khinkal.locationNotifier.R
import ru.khinkal.locationNotifier.core.ext.location.distanceInMetersTo
import ru.khinkal.locationNotifier.core.location.LocationService
import ru.khinkal.locationNotifier.core.location.model.GeoPoint
import ru.khinkal.locationNotifier.core.notification.AndroidNotificationService
import ru.khinkal.locationNotifier.core.utill.DistanceFormatter
import ru.khinkal.locationNotifier.core.utill.ext.cancelService
import ru.khinkal.locationNotifier.core.utill.ext.isServiceActive
import ru.khinkal.locationNotifier.core.utill.ext.isUseFullScreenIntentPermissionGranted
import ru.khinkal.locationNotifier.core.vibration.VibrationService
import ru.khinkal.locationNotifier.feature.goalBroadcaster.di.createGoalGeoPointBroadcasterGraph
import ru.khinkal.locationNotifier.feature.goalBroadcaster.di.deps.GoalGeoPointBroadcasterDeps
import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint
import ru.khinkal.locationNotifier.feature.main.presentation.alarm.GoalReachedActivity

class GoalGeoPointBroadcastForegroundService : Service() {

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    private var furthestGeoPoint: GeoPoint? = null
    private var locationObserverJob: Job? = null

    private lateinit var locationService: LocationService
    private lateinit var notificationService: AndroidNotificationService
    private lateinit var vibrationService: VibrationService

    private val actionsBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.action) {
                ACTION_STOP_SERVICE -> {
                    this@GoalGeoPointBroadcastForegroundService.stopSelf()
                }
            }
        }
    }

    override fun onCreate() {
        val deps = object : GoalGeoPointBroadcasterDeps {
            override val systemDeps = AndroidSystemDeps(baseContext)
            override val coroutineScope =
                this@GoalGeoPointBroadcastForegroundService.coroutineScope
        }
        val graph = createGoalGeoPointBroadcasterGraph(deps = deps)
        locationService = graph.locationService
        notificationService = graph.notificationService as AndroidNotificationService
        vibrationService = graph.vibrationService

        registerActionsReceiver()
        super.onCreate()
    }

    private fun registerActionsReceiver() {
        ContextCompat.registerReceiver(
            baseContext,
            actionsBroadcastReceiver,
            IntentFilter().apply {
                addAction(ACTION_STOP_SERVICE)
            },
            ContextCompat.RECEIVER_NOT_EXPORTED,
        )
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

    @SuppressLint("FullScreenIntentPolicy")
    private fun GoalGeoPoint.createForegroundNotificationBuilder(): NotificationCompat.Builder {
        val stopAction = createStopBroadcastAction()
        val fullScreenPendingIntent = createFullScreenPendingIntent()

        return notificationService.notification(name) {
            setOngoing(true)
            setOnlyAlertOnce(true)
            setCategory(NotificationCompat.CATEGORY_ALARM)
            setPriority(NotificationCompat.PRIORITY_HIGH)
            setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            setFullScreenIntent(fullScreenPendingIntent, true)
            setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            setRequestPromotedOngoing(true)
            addAction(stopAction)
        }
    }

    private fun createStopBroadcastAction(): NotificationCompat.Action {
        val stopIntent = createActionPendingIntent(ACTION_STOP_SERVICE)

        return NotificationCompat.Action.Builder(
            android.R.drawable.ic_delete,
            getString(R.string.notification_action_stop),
            stopIntent,
        )
            .build()
    }

    private fun createFullScreenPendingIntent(): PendingIntent {
        val fullScreenIntent = Intent(baseContext, GoalReachedActivity::class.java).apply {
            putExtra(GOAL_NAME_KEY, name)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        return PendingIntent.getActivity(
            baseContext,
            0,
            fullScreenIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
    }

    private fun observeLocation(
        goalGeoPoint: GoalGeoPoint,
        foregroundNotificationBuilder: NotificationCompat.Builder,
    ) {
        locationObserverJob?.cancel()
        locationObserverJob = locationService.geoPoint
            .filterNotNull()
            .onEach { geoPoint ->
                val metersToGoal = geoPoint distanceInMetersTo goalGeoPoint.geoPoint
                val furthestDistance = furthestGeoPoint
                    ?.distanceInMetersTo(goalGeoPoint.geoPoint) ?: 0
                if (metersToGoal > furthestDistance) {
                    furthestGeoPoint = geoPoint
                }

                if (metersToGoal <= goalGeoPoint.meters) {
                    onReachGoal(
                        goalGeoPoint = goalGeoPoint,
                    )
                } else {
                    val totalDistance = furthestGeoPoint?.distanceInMetersTo(goalGeoPoint.geoPoint)
                        ?: metersToGoal

                    onProgressToReachGoal(
                        metersToGoal = metersToGoal,
                        totalDistance = totalDistance,
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
            .also { locationObserverJob = it }
    }

    private fun onReachGoal(
        goalGeoPoint: GoalGeoPoint,
    ) {
        val notification = createGoalReachedNotification(goalName = goalGeoPoint.name)

        notificationService.notify(FOREGROUND_FINISH_NOTIFICATION_ID, notification.build())
        stopSelf()
    }

    private fun createGoalReachedNotification(
        goalName: String,
    ): NotificationCompat.Builder {
        return notificationService.notification(
            title = getString(R.string.goal_reached_notification_title, goalName),
        ) {
            setCategory(NotificationCompat.CATEGORY_ALARM)
            setPriority(NotificationCompat.PRIORITY_MAX)
            setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            setShowReachGoalFullScreenIfCan(goalName)
            setVibrate(longArrayOf(0, 1000))
        }
    }

    @SuppressLint("FullScreenIntentPolicy")
    private fun NotificationCompat.Builder.setShowReachGoalFullScreenIfCan(
        goalName: String,
    ): NotificationCompat.Builder = apply {
        val canUseFullScreenIntent = notificationService.canUseFullScreenIntent()
        if (!canUseFullScreenIntent || !isUseFullScreenIntentPermissionGranted()) return@apply
        val fullScreenPendingIntent = createReachGoalFullScreenPendingIntent(goalName)

        setFullScreenIntent(fullScreenPendingIntent, true)
    }

    private fun createReachGoalFullScreenPendingIntent(goalName: String): PendingIntent {
        val intent = createReachGoalFullScreenIntent(goalName)

        return PendingIntent.getActivity(
            baseContext,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
    }

    private fun createReachGoalFullScreenIntent(goalName: String): Intent {
        return GoalReachedActivity.createIntent(
            context = baseContext,
            goalName = goalName,
        ).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
    }

    private fun onProgressToReachGoal(
        metersToGoal: Int,
        totalDistance: Int,
        foregroundNotificationBuilder: NotificationCompat.Builder,
    ) {
        val progress = if (totalDistance > 0) {
            ((totalDistance - metersToGoal) * 100 / totalDistance).coerceIn(0, 100)
        } else {
            100
        }

        val notification = foregroundNotificationBuilder
            .setOnProgressToReachGoal(metersToGoal, progress, baseContext)
            .build()

        notificationService.notify(
            id = FOREGROUND_ACTIVE_NOTIFICATION_ID,
            notification = notification,
        )
    }

    private fun NotificationCompat.Builder.setOnProgressToReachGoal(
        metersToGoal: Int,
        progress: Int,
        context: Context,
    ): NotificationCompat.Builder {
        return apply {
            setStyle(
                NotificationCompat.ProgressStyle()
                    .setProgress(progress)
                    .setProgressIndeterminate(false),
            )
            setContentText(DistanceFormatter.format(metersToGoal, context, isForChip = false))
            setShortCriticalText(DistanceFormatter.format(metersToGoal, context, isForChip = true))
            setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            setCategory(NotificationCompat.CATEGORY_STATUS)
            setPriority(NotificationCompat.PRIORITY_HIGH)
        }
    }

    override fun onDestroy() {
        coroutineScope.cancel()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun createActionPendingIntent(action: String): PendingIntent {
        val intent = Intent(baseContext, this::class.java).apply {
            this.action = action
        }
        return PendingIntent.getService(
            baseContext,
            action.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
    }

    companion object {

        private const val FOREGROUND_ACTIVE_NOTIFICATION_ID = 2
        private const val FOREGROUND_FINISH_NOTIFICATION_ID = 3
        private const val GOAL_GEO_POINT_KEY = "GOAL_GEO_POINT_KEY"
        private const val GOAL_NAME_KEY = "GOAL_NAME_KEY"

        const val ACTION_STOP_SERVICE = "ru.khinkal.locationNotifier.ACTION_STOP_BROADCAST"

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
                context.cancelService<GoalGeoPointBroadcastForegroundService>()
                return
            }
            val geoPointJson = Json.encodeToString(goalGeoPoint)
            val intent = Intent(
                context,
                GoalGeoPointBroadcastForegroundService::class.java
            ).apply {
                putExtra(GOAL_GEO_POINT_KEY, geoPointJson)
            }

            ContextCompat.startForegroundService(context, intent)
        }
    }
}
