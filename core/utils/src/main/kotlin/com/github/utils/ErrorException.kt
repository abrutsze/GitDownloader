package com.github.utils

sealed class ErrorException : Throwable() {
    data class NetError(val code: Int, override val message: String) : ErrorException()
    data class ErrorMessage(override val message: String?) : ErrorException()
    data object InternalServerError : ErrorException()
    data object UnknownError : ErrorException()
    data object EmptyList : ErrorException()

    // todo should we remove this
    data class SmsCodeBlockError(override val message: String) : ErrorException()
    data class WrongSmsCodeError(override val message: String) : ErrorException()
    data object ReplacementNotFoundException : ErrorException()
}

fun Throwable.toErrorException(): ErrorException =
    this as? ErrorException ?: ErrorException.UnknownError
