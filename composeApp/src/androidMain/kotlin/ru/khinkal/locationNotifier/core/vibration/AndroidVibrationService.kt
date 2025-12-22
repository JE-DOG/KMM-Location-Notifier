package ru.khinkal.locationNotifier.core.vibration

import android.content.Context
import android.os.Build
import android.os.CombinedVibration
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

class AndroidVibrationService(
    private val context: Context
) : VibrationService {

    private val vibrationManager by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        } else {
            context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }
    }

    override fun vibrate() {
        val seconds = 2_000L
        val vibrationEffect =
            VibrationEffect.createOneShot(
                /* milliseconds = */ seconds,
                /* amplitude = */ 5,
            )

        vibrate(
            onApi31AndAbove = { vibrationManager ->
                val combinedVibration = CombinedVibration.createParallel(vibrationEffect)
                vibrationManager.vibrate(combinedVibration)
            },
            onApi30AndLess = { vibrator ->
                vibrator.vibrate(seconds)
            },
        )
    }

    private fun vibrate(
        onApi31AndAbove: (VibratorManager) -> Unit,
        onApi30AndLess: (Vibrator) -> Unit,
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibrationManager = vibrationManager as VibratorManager
            onApi31AndAbove(vibrationManager)
        } else {
            val vibrator = vibrationManager as Vibrator
            onApi30AndLess(vibrator)
        }
    }
}
