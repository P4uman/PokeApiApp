package com.app.pokeapi.entities

import com.google.gson.annotations.SerializedName

data class TypeEntityList(
    @SerializedName("results") val list: List<TypeEntity>
)

data class TypeEntity(
    @SerializedName("name") val type: String,
    @SerializedName("url") val infoURL: String
)