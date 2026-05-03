package ru.khinkal.locationNotifier.common.android.intent.util

import android.content.Context
import android.content.Intent

fun Intent.setApplicationPackage(context: Context): Intent {
    return setPackage(context.packageName)
}
