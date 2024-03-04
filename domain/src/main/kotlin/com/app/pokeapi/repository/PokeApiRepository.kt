package com.app.pokeapi.repository

import com.app.pokeapi.model.PokemonShortModel
import com.app.pokeapi.model.type.TypeModel

interface PokeApiRepository {

    suspend fun getTypeList() : Result<List<TypeModel>>
    suspend fun getTypeDetail(typeID: String): Result<List<PokemonShortModel>>
}