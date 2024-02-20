package com.app.pokeapi.service

import com.app.pokeapi.entities.TypeDetailEntity
import com.app.pokeapi.entities.TypeEntityList
import javax.inject.Inject

class PokeApiService
@Inject constructor(
    private val pokeApi: PokeApi
) {

    suspend fun getTypeList(): TypeEntityList? = pokeApi.getTypes().body()
    suspend fun getTypeDetail(typeID: String): TypeDetailEntity? =
        pokeApi.getTypeDetail(typeID).body()
}