package com.app.pokeapi.pokeapi.domain.repository

import com.app.pokeapi.entities.TypeDetailEntity
import com.app.pokeapi.entities.TypeEntityList

interface PokeApiRepository {

    suspend fun getTypeList() : TypeEntityList?
    suspend fun getTypeDetail(typeID: String): TypeDetailEntity?
}