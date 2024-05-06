package com.github.network.impl.extention

import kotlinx.coroutines.flow.flow

fun <T> emitFlow(action: suspend () -> T) = flow { emit(action.invoke()) }