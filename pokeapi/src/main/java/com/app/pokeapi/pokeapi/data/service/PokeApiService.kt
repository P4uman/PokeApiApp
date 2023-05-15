package com.app.pokeapi.pokeapi.data.service

import com.app.pokeapi.pokeapi.data.entities.TypeDetailEntity
import com.app.pokeapi.pokeapi.data.entities.TypeEntityList
import javax.inject.Inject

class PokeApiService
@Inject constructor(
    private val pokeApi: PokeApi
) {

    suspend fun getTypeList(): TypeEntityList? = pokeApi.getTypes().body()
    suspend fun getTypeDetail(typeID: String): TypeDetailEntity? =
        pokeApi.getTypeDetail(typeID).body()
}