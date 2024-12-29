package ru.khinkal.locationNotifier.core.vibration

import android.content.Context
import android.os.Build
import android.os.CombinedVibration
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

class VibrationServiceImpl(
    private val context: Context
): VibrationService {

    override fun vibrate(
        interval: Long
    ) {
        val vibrationEffect =
            VibrationEffect.createOneShot(
                interval,
                VibrationEffect.DEFAULT_AMPLITUDE,
            )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            val combinedVibration = CombinedVibration.createParallel(vibrationEffect)

            vibratorManager.vibrate(combinedVibration)
        } else {
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

            vibrator.vibrate(vibrationEffect)
        }
    }
}