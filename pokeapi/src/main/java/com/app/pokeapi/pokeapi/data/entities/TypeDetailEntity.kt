package com.app.pokeapi.pokeapi.data.entities

import com.google.gson.annotations.SerializedName

data class TypeDetailEntity(
    @SerializedName("id") val ID: Int,
    @SerializedName("pokemon") val pokemonList: List<PokemonShortEntity>
)