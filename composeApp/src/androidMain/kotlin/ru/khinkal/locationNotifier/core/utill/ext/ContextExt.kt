package ru.khinkal.locationNotifier.core.utill.ext

import android.app.ActivityManager
import android.app.ActivityManager.RunningServiceInfo
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.getSystemService

inline fun <reified S : Service> Context.isServiceActive(): Boolean {
    val activityManager = requireNotNull(getSystemService<ActivityManager>())
    for (service: RunningServiceInfo in activityManager.getRunningServices(Int.MAX_VALUE)) {
        if (service.service.className == S::class.java.name) {
            return true
        }
    }

    return false
}

inline fun <reified S : Service> Context.cancelServie() {
    val intent = Intent(this, S::class.java)
    stopService(intent)
}

fun Context.isPermissionGranted(permission: String): Boolean {
    val permissionResult = ActivityCompat.checkSelfPermission(this, permission)
    return permissionResult == PackageManager.PERMISSION_GRANTED
}

fun Context.isUseFullScreenIntentPermissionGranted(): Boolean {
    return isPermissionGranted(android.Manifest.permission.USE_FULL_SCREEN_INTENT)
}
