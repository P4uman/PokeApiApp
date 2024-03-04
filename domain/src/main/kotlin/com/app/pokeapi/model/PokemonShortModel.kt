package com.app.pokeapi.model

import com.app.pokeapi.entities.TypeDetailEntity

data class PokemonShortModel(
    val pokemonName: String
)

fun TypeDetailEntity.toDomain() =
    pokemonList.map { PokemonShortModel(pokemonName = it.pokemon.name) }