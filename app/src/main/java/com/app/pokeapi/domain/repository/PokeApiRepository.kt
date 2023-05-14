package com.app.pokeapi.domain.repository

import com.app.pokeapi.data.entities.TypeEntityList

interface PokeApiRepository {

    suspend fun getTypeList() : TypeEntityList?
}