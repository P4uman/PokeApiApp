package com.app.pokeapi.pokeapi.domain.repository

import com.app.pokeapi.entities.TypeDetailEntity
import com.app.pokeapi.entities.TypeEntityList
import com.app.pokeapi.service.PokeApiService
import javax.inject.Inject

class PokeApiRepositoryImp @Inject constructor(
    private val service: PokeApiService
) : PokeApiRepository {

    override suspend fun getTypeList(): TypeEntityList? =
        service.getTypeList()

    override suspend fun getTypeDetail(typeID: String): TypeDetailEntity? =
        service.getTypeDetail(typeID)
}