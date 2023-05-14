package com.app.pokeapi.domain.useCase

import com.app.pokeapi.data.entities.TypeDetailEntity
import com.app.pokeapi.data.service.PokeApiService
import com.app.pokeapi.domain.model.PokemonShortModel
import com.app.pokeapi.domain.model.TypeDetailModel
import javax.inject.Inject

class GetTypeDetailUseCase
@Inject constructor(
    private val service: PokeApiService
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
            pokemonList = entity.pokemonList.map { pokemonShortEntity ->
                PokemonShortModel(pokemonShortEntity.pokemon.name)
            }
        )
    }
}