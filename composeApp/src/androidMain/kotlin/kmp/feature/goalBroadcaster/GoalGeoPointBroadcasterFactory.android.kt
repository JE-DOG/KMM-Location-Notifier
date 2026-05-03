package kmp.feature.goalBroadcaster

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.core.content.ContextCompat
import kmp.core.asAndroid
import kmp.core.deps.SystemDeps
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.khinkal.locationNotifier.feature.goalBroadcaster.GoalGeoPointBroadcaster
import ru.khinkal.locationNotifier.feature.goalBroadcaster.model.GoalBroadcastProgress
import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint
import ru.khinkal.locationNotifier.feature.main.presentation.broadcast.GoalGeoPointBroadcastForegroundService

actual object GoalGeoPointBroadcasterFactory {

    @Suppress("UNUSED_PARAMETER")
    actual fun create(
        systemDeps: SystemDeps,
        coroutineScope: CoroutineScope,
    ): GoalGeoPointBroadcaster {
        return AndroidGoalGeoPointBroadcaster(
            context = systemDeps.asAndroid().applicationContext,
        )
    }
}

private class AndroidGoalGeoPointBroadcaster(
    private val context: Context,
) : GoalGeoPointBroadcaster {

    private val _activeGoalProgress = MutableStateFlow<GoalBroadcastProgress?>(null)
    override val activeGoalProgress: StateFlow<GoalBroadcastProgress?> = _activeGoalProgress
        .asStateFlow()

    private val progressBroadcastReceiver by lazy {
        createProgressBroadcastReceiver()
    }

    private fun createProgressBroadcastReceiver(): BroadcastReceiver {
        return object : BroadcastReceiver() {

            override fun onReceive(context: Context, intent: Intent) {
                when (intent.action) {
                    GoalGeoPointBroadcastForegroundService.ACTION_PROGRESS_UPDATE -> {
                        val goalName = intent.getStringExtra(
                            GoalGeoPointBroadcastForegroundService.EXTRA_GOAL_NAME,
                        ) ?: return
                        val metersToGoal = intent.getIntExtra(
                            GoalGeoPointBroadcastForegroundService.EXTRA_METERS_TO_GOAL,
                            0,
                        )
                        val progress = intent.getFloatExtra(
                            GoalGeoPointBroadcastForegroundService.EXTRA_PROGRESS,
                            0f,
                        )
                        _activeGoalProgress.update {
                            GoalBroadcastProgress(
                                goalName = goalName,
                                metersToGoal = metersToGoal,
                                progress = progress,
                            )
                        }
                    }

                    GoalGeoPointBroadcastForegroundService.ACTION_BROADCAST_FINISH ->
                        _activeGoalProgress.update { null }
                }
            }
        }
    }

    override fun startBroadcast(goalGeoPoint: GoalGeoPoint) {
        ContextCompat.registerReceiver(
            context,
            progressBroadcastReceiver,
            IntentFilter().apply {
                addAction(GoalGeoPointBroadcastForegroundService.ACTION_PROGRESS_UPDATE)
                addAction(GoalGeoPointBroadcastForegroundService.ACTION_BROADCAST_FINISH)
            },
            ContextCompat.RECEIVER_NOT_EXPORTED,
        )

        GoalGeoPointBroadcastForegroundService.start(
            context = context,
            goalGeoPoint = goalGeoPoint,
        )
    }
}
