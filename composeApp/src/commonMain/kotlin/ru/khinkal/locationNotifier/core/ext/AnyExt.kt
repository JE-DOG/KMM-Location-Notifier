package ru.khinkal.locationNotifier.core.ext

import kotlin.coroutines.cancellation.CancellationException

/**
 * Executes the specified block and returns a [Result] encapsulating the outcome.
 * If the block completes normally, the result is wrapped in [Result.success].
 * If an exception is thrown, it is wrapped in [Result.failure], unless it's a [CancellationException],
 * which is rethrown to propagate cancellation. See https://github.com/Kotlin/kotlinx.coroutines/issues/1814
 *
 * @param T The receiver type on which the block is to be executed.
 * @param R The type of the result produced by the block.
 * @param block The block of code to execute, which can return a result or throw an exception.
 * @return [Result] containing either the result of the block or an exception that was thrown,
 *         excluding [CancellationException].
 * @throws CancellationException if this specific exception is thrown by the block.
 */
inline fun <T, R> T.runCatchingCancellable(block: T.() -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Throwable) {
        Result.failure(e)
    }
}
