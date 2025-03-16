package kmp.storage.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath

expect fun getDataStorePath(): String

object DataStoreHelper {

    private val INSTANCES = hashMapOf<String, DataStore<Preferences>>()

    fun createDataStore(
        name: String,
    ): DataStore<Preferences> =
        INSTANCES.getOrPut(key = name) {
            PreferenceDataStoreFactory.createWithPath(
                produceFile = {
                    val fileName = createDataStoreFileName(name)
                    getDataStorePath().toPath().resolve(fileName)
                },
            )
        }

    private fun createDataStoreFileName(name: String) =
        "$name.preferences_pb"
}
