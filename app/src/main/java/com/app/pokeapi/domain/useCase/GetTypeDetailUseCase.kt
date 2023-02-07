package com.app.pokeapi.domain.useCase

import com.app.pokeapi.data.network.TypeDetailService
import com.app.pokeapi.data.network.entities.TypeDetailEntity
import com.app.pokeapi.domain.model.PokemonShortModel
import com.app.pokeapi.domain.model.TypeDetailModel
import javax.inject.Inject

class GetTypeDetailUseCase
@Inject constructor(
    private val service: TypeDetailService
) {

    suspend operator fun invoke(typeID: String): TypeDetailModel {
        val model = service.getTypeDetail(typeID)?.let { entity ->
            mapToDomain(entity)
        } ?: TypeDetailModel(emptyList())

        return model
    }

    private fun mapToDomain(
        entity: TypeDetailEntity
    ): TypeDetailModel {
        return TypeDetailModel(
            pokemonList = entity.pokemonList?.mapNotNull { pokemonShortEntity ->
                pokemonShortEntity.pokemon?.name?.let { name ->
                    PokemonShortModel(name)
                }
            } ?: emptyList()
        )
    }
}