package com.app.pokeapi.domain.model

import com.app.pokeapi.core.TypeEnum

data class TypeModel(
    val typeName: String,
    val linkURL: String,
    val type: TypeEnum
)