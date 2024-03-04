package com.app.pokeapi.core

import retrofit2.Response

fun <T> Response<T>.mapNetworkResponse(): Result<T> {
    val result: T? = this.body()

    return when {
        this.isSuccessful && result != null -> Result.success(result)
        this.isSuccessful && result == null -> Result.failure(NetworkExceptions.NullResponse)
        this.isSuccessful.not() -> Result.failure(
            NetworkExceptions.NetworkError(
                ResponseCodes.fromStringCode(
                    this.errorBody()?.string()
                )
            )
        )

        else -> Result.failure(NetworkExceptions.UnknownError)
    }
}