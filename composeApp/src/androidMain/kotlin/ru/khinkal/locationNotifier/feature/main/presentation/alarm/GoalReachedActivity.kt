package ru.khinkal.locationNotifier.feature.main.presentation.alarm

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.content.getSystemService
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.khinkal.locationNotifier.shared.theme.AppTheme

class GoalReachedActivity : ComponentActivity() {

    private var vibrator: Vibrator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setupWindow()
        super.onCreate(savedInstanceState)
        startVibrationLoop()
        val goalName = intent.getStringExtra(GOAL_NAME_KEY) ?: ""

        setContent {
            AppTheme {
                GoalReachedScreen(
                    goalName = goalName,
                    onCancelClick = { finish() },
                )
            }
        }
    }

    private fun setupWindow() {
        this.setShowWhenLocked(true)
        this.setTurnScreenOn(true)
    }

    private fun startVibrationLoop() {
        initVibrator()
        val vibrator = vibrator ?: return
        if (!vibrator.hasVibrator()) return

        lifecycleScope.launch {
            repeat(7) {
                vibrator.vibrate(
                    VibrationEffect.createOneShot(
                        VIBRATION_MILLS,
                        VibrationEffect.DEFAULT_AMPLITUDE,
                    )
                )

                delay(VIBRATION_MILLS + VIBRATION_DELAY_MILLS)
            }
        }
    }

    private fun initVibrator() {
        vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            getSystemService<VibratorManager>()?.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            getSystemService<Vibrator>()
        }
    }

    override fun onStop() {
        vibrator?.cancel()
        super.onStop()
    }

    companion object {

        private const val VIBRATION_MILLS = 1_000L
        private const val VIBRATION_DELAY_MILLS = 500L

        fun createIntent(
            context: Context,
            goalName: String,
        ): Intent {
            return Intent(context, GoalReachedActivity::class.java).apply {
                putExtra(GOAL_NAME_KEY, goalName)
            }
        }

        private const val GOAL_NAME_KEY = "GOAL_NAME_KEY"
    }
}
