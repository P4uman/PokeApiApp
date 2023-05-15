package com.app.pokeapi.pokeapi.domain.model

import com.app.pokeapi.pokeapi.domain.useCase.getTypeList.model.TypeEnum

data class TypeModel(
    val typeName: String,
    val linkURL: String,
    val type: TypeEnum
)