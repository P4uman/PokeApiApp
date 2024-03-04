package com.app.pokeapi.core

enum class ResponseCodes(code: String) {
    BAD_REQUEST("400"),
    UNAUTHORIZED("401"),
    FORBIDDEN("403"),
    NOT_FOUND("404"),
    CONFLICT("409"),
    GONE("410"),
    INTERNAL_SERVER_ERROR("500"),
    UNKNOWN("");

    companion object {
        fun fromStringCode(code: String?): ResponseCodes =
            ResponseCodes.values().find {
                it.toString() == code
            } ?: UNKNOWN
    }
}