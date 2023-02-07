package com.app.pokeapi.data.network.entities

import com.google.gson.annotations.SerializedName

data class PokemonShortEntity(
    @SerializedName("pokemon") val pokemon: PokemonShortInnerEntity?
)

data class PokemonShortInnerEntity(
    @SerializedName("name") val name: String?
)
