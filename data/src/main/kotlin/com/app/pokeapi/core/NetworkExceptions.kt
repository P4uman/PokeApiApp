package com.app.pokeapi.core

sealed class NetworkExceptions() : Exception() {
    object NullResponse : NetworkExceptions()
    data class NetworkError(val code: ResponseCodes) : NetworkExceptions()
    object UnknownError: NetworkExceptions()
}