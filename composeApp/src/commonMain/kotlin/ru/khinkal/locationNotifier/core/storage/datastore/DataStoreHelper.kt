package ru.khinkal.locationNotifier.core.storage.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kmp.core.path.PathManager

object DataStoreHelper {

    private val instances = hashMapOf<String, DataStore<Preferences>>()

    fun create(
        name: String,
        pathManager: PathManager,
    ): DataStore<Preferences> =
        instances.getOrPut(key = name) {
            PreferenceDataStoreFactory.createWithPath(
                produceFile = {
                    val fileName = createDataStoreFileName(name)
                    pathManager.dataPath.resolve(fileName)
                },
            )
        }

    private fun createDataStoreFileName(name: String) =
        "$name.preferences_pb"
}
