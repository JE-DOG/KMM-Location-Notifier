package ru.khinkal.locationNotifier.core.utill.ext

import android.app.ActivityManager
import android.app.ActivityManager.RunningServiceInfo
import android.app.Service
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

fun Context.checkPermission(
    permission: String,
    onPermissionDenied: () -> Unit = {},
    onPermissionGranted: () -> Unit
) {
    val permissionResult = ActivityCompat.checkSelfPermission(this, permission)
    if (permissionResult == PackageManager.PERMISSION_GRANTED) {
        onPermissionGranted()
    } else {
        onPermissionDenied()
    }

}

fun <S : Service> Context.isServiceActive(serviceClass: Class<S>): Boolean {
    val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    for (service: RunningServiceInfo in activityManager.getRunningServices(Int.MAX_VALUE)) {
        if (service.service.className == serviceClass.name) {
            return true
        }
    }

    return false
}