package com.app.pokeapi.service

import com.app.pokeapi.core.mapNetworkResponse
import com.app.pokeapi.entities.TypeDetailEntity
import com.app.pokeapi.entities.TypeEntityList
import javax.inject.Inject

class PokeApiService
@Inject constructor(
    private val pokeApi: PokeApi
) {

    suspend fun getTypeList(): Result<TypeEntityList> = pokeApi.getTypes().mapNetworkResponse()
    suspend fun getTypeDetail(typeID: String): Result<TypeDetailEntity> =
        pokeApi.getTypeDetail(typeID).mapNetworkResponse()
}