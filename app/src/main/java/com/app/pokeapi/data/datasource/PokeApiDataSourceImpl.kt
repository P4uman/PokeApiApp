package com.app.pokeapi.data.datasource

import com.app.pokeapi.data.entities.TypeDetailEntity
import com.app.pokeapi.data.entities.TypeEntityList
import com.app.pokeapi.data.service.PokeApiService
import javax.inject.Inject

class PokeApiDataSourceImpl @Inject constructor(
    private val service: PokeApiService
) : PokeApiDataSource {

    override suspend fun getTypeList(): Result<TypeEntityList?> =
        runCatching {
            service.getTypeList()
        }

    override suspend fun getTypeDetail(typeID: String): Result<TypeDetailEntity?> =
        runCatching {
            service.getTypeDetail(typeID)
        }


}