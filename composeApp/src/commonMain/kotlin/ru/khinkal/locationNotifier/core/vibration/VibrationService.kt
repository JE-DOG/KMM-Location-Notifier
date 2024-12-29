package ru.khinkal.locationNotifier.core.vibration

interface VibrationService {

    /**
     * @param interval vibration interval
     * */
    fun vibrate(interval: Long)

}