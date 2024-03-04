package com.app.pokeapi.repository

import com.app.pokeapi.model.toDomain
import com.app.pokeapi.model.type.TypeModel
import com.app.pokeapi.model.type.toDomain
import com.app.pokeapi.service.PokeApiService
import javax.inject.Inject

class PokeApiRepositoryImp @Inject constructor(
    private val service: PokeApiService
) : PokeApiRepository {

    override suspend fun getTypeList(): Result<List<TypeModel>> =
        service.getTypeList().map { it.toDomain() }

    override suspend fun getTypeDetail(typeID: String) =
        service.getTypeDetail(typeID).map { it.toDomain() }
}