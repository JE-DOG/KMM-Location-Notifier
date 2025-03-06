package ru.khinkal.locationNotifier.core.ext.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch
import ru.khinkal.locationNotifier.core.ext.runCatchingCancellable
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun CoroutineScope.launchCatching(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    onFailure: suspend (Throwable) -> Unit = {},
    onFinally: suspend () -> Unit = {},
    action: suspend CoroutineScope.() -> Unit,
) = launch(
    context = context,
    start = start,
) {
    runCatchingCancellable {
        action()
    }
        .onFailure { exception ->
            onFailure(exception)
        }
    onFinally()
}
