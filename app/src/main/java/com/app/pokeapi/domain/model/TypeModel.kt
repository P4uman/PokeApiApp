package com.app.pokeapi.domain.model

import com.app.pokeapi.core.TypeEnum
import com.app.pokeapi.domain.core.BaseModel

data class TypeModel(
    val typeName: String,
    val linkURL: String,
    val type: TypeEnum
)

data class TypeListModel(
    val list: List<TypeModel>
) : BaseModel()