package com.github.dispatchers.provider

import kotlinx.coroutines.CoroutineDispatcher
import kotlin.coroutines.CoroutineContext

/**
 * If current context is CoroutineDispatcher then return this
 */
val CoroutineContext.coroutineDispatcher: CoroutineDispatcher
    get() {
        if (this !is CoroutineDispatcher) {
            error("This dispatcher not CoroutineDispatcher!")
        }

        return this
    }
