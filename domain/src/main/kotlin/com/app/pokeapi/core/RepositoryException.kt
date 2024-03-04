package com.app.pokeapi.core

sealed class RepositoryException(override val message: String?) : Exception(message) {
    object UNKOWN_ERROR: RepositoryException("")
}