package com.app.pokeapi.domain.repository

import com.app.pokeapi.data.datasource.PokeApiDataSource
import com.app.pokeapi.data.entities.TypeEntityList
import javax.inject.Inject

class PokeApiRepositoryImp @Inject constructor(
    private val dataSource: PokeApiDataSource
) : PokeApiRepository {

    override suspend fun getTypeList(): TypeEntityList? =
        dataSource.getTypeList()
}