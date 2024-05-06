package com.github.utils


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun <T> Flow<T>.subscribe(
    scope: CoroutineScope,
    success: (value: T) -> Unit
) = scope.launch { subscribe { success.invoke(it) } }

fun <T> Flow<T>.subscribe(
    scope: CoroutineScope,
    success: suspend (value: T) -> Unit,
    error: suspend (Throwable) -> Unit = { },
    onStart: suspend () -> Unit = { },
    complete: () -> Unit = { }
) = scope.launch {
     subscribe(
        success = { success.invoke(it) },
        error = { error.invoke(it) },
        onStart = { onStart.invoke()}
    )
}.apply { invokeOnCompletion { complete.invoke() } }

suspend fun <T> Flow<T>.subscribe(
    success: suspend (value: T) -> Unit,
    error: suspend (Throwable) -> Unit = { },
    onStart: (suspend () -> Unit)? = null,
    onEnd: (suspend () -> Unit)? = null,
) {
    onStart?.invoke()
    try {
        collect {
            success.invoke(it)
            onEnd?.invoke()
        }
    } catch (throwable: Throwable) {
        error.invoke(throwable)
        onEnd?.invoke()
        throwable.printStackTrace()
    }
}

suspend fun <T> Flow<T>.subscribe(success: suspend (value: T) -> Unit) {
    collect { success.invoke(it) }
}
