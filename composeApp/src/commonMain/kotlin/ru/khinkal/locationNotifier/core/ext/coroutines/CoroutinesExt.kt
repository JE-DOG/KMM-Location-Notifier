package ru.khinkal.locationNotifier.core.ext.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.khinkal.locationNotifier.core.ext.runCatchingCancellable
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.properties.Delegates

fun canceledJob() = Delegates.observable<Job?>(null) { _, old, _ -> old?.cancel() }

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
