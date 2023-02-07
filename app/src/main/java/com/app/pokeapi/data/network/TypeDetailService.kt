package com.app.pokeapi.data.network

import com.app.pokeapi.data.network.entities.TypeDetailEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TypeDetailService
@Inject constructor(
    private val pokeApi: PokeApi
) {

    suspend fun getTypeDetail(typeID: String): TypeDetailEntity? {
        return withContext(Dispatchers.IO) {
            pokeApi.getTypeDetail(typeID).body()
        }
    }
}