package ru.khinkal.locationNotifier.shared.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember

class NavResultStore {

    private val results = mutableStateMapOf<String, Any?>()

    @Composable
    fun <T> getResult(screenKey: String): State<T?> {
        @Suppress("UNCHECKED_CAST") val result = results[screenKey] as? T
        val resultState = remember(screenKey, result) {
            derivedStateOf {
                results.remove(screenKey)
                result
            }
        }

        return resultState
    }

    fun setResult(screenKey: String, result: Any?) {
        results[screenKey] = result
    }
}

@Composable
fun rememberNavResultStore(): NavResultStore {
    return remember {
        NavResultStore()
    }
}
