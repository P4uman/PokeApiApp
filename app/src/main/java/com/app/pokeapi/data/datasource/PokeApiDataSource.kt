package com.app.pokeapi.data.datasource

import com.app.pokeapi.data.entities.TypeDetailEntity
import com.app.pokeapi.data.entities.TypeEntityList

interface PokeApiDataSource {
    suspend fun getTypeList(): TypeEntityList?
    suspend fun getTypeDetail(typeID: String): TypeDetailEntity?
}