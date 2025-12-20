package ru.khinkal.locationNotifier.core.navigation

import androidx.navigation.NavType
import androidx.savedstate.SavedState
import androidx.savedstate.read
import androidx.savedstate.write
import kotlinx.serialization.json.Json

inline fun <reified T> serializableNavType(
    isNullableAllowed: Boolean = false,
    json: Json = Json,
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {

    override fun put(bundle: SavedState, key: String, value: T) {
        bundle.write {
            this.putString(key, json.encodeToString(value))
        }
    }

    override fun get(bundle: SavedState, key: String): T? {
        return bundle.read {
            val valueJson = this.getString(key)

            json.decodeFromString<T>(valueJson)
        }
    }

    override fun parseValue(value: String): T {
        return json.decodeFromString(value)
    }

    override fun serializeAsValue(value: T): String {
        return json.encodeToString(value)
    }
}