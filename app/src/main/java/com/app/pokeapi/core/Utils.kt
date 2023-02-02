package com.app.pokeapi.core

import java.lang.Exception

/** Extracts id reference from an URL
Example URL "https://pokeapi.co/api/v2/type/13/" it would return 13 */
fun extractIDFromURl(url: String): Int? {
    val splitURL = url.split("/")
    return try {
        splitURL[splitURL.size-2].toInt()
    } catch (e: Exception) {
        null
    }
}