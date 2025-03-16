package kmp.storage.datastore

import ru.khinkal.locationNotifier.LocationNotifierApp

actual fun getDataStorePath(): String {
    val applicationContext = LocationNotifierApp.INSTANCE
    return applicationContext.filesDir.absolutePath
}
