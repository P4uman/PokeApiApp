package com.app.pokeapi.pokeapi.domain.repository

import com.app.pokeapi.pokeapi.data.datasource.PokeApiDataSource
import com.app.pokeapi.pokeapi.data.entities.TypeDetailEntity
import com.app.pokeapi.pokeapi.data.entities.TypeEntityList
import javax.inject.Inject

class PokeApiRepositoryImp @Inject constructor(
    private val dataSource: PokeApiDataSource
) : PokeApiRepository {

    override suspend fun getTypeList(): TypeEntityList? =
        dataSource.getTypeList()

    override suspend fun getTypeDetail(typeID: String): TypeDetailEntity? =
        dataSource.getTypeDetail(typeID)
}