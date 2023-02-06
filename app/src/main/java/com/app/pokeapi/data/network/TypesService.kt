package com.app.pokeapi.data.network

import com.app.pokeapi.data.network.entities.TypeEntityList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TypesService
@Inject constructor(
    private val pokeApi: PokeApi
) {

    suspend fun getTypeList(): TypeEntityList? {
        // Execute call in another thread using coroutines
        return withContext(Dispatchers.IO) {
            pokeApi.getTypes().body()
        }
    }
}