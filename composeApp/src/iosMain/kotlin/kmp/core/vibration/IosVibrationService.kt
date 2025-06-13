package kmp.core.vibration

import platform.UIKit.UIImpactFeedbackGenerator
import platform.UIKit.UIImpactFeedbackStyle
import ru.khinkal.locationNotifier.core.vibration.VibrationService

class IosVibrationService : VibrationService {

    private val vibrationManager = UIImpactFeedbackGenerator(
        style = UIImpactFeedbackStyle.UIImpactFeedbackStyleHeavy,
    )

    init {
        vibrationManager.prepare()
    }

    override fun vibrate() {
        vibrationManager.impactOccurredWithIntensity(1.0)
    }
}
