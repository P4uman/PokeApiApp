package com.app.pokeapi.domain.repository

import com.app.pokeapi.data.entities.TypeDetailEntity
import com.app.pokeapi.data.entities.TypeEntityList
import com.app.pokeapi.domain.model.TypeDetailModel

interface PokeApiRepository {

    suspend fun getTypeList() : TypeEntityList?
    suspend fun getTypeDetail(typeID: String): TypeDetailEntity?
}