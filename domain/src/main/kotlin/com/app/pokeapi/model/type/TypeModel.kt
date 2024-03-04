package com.app.pokeapi.model.type

import com.app.pokeapi.entities.TypeEntityList

data class TypeModel(
    val name: String,
    val infoURL: String,
    val type: TypeEnum
)

fun TypeEntityList.toDomain(): List<TypeModel> = list.mapNotNull { typeEntity ->
    TypeEnum.fromString(typeEntity.type)?.let { typeEnum ->
        TypeModel(
            name = typeEntity.type,
            infoURL = typeEntity.infoURL,
            type = typeEnum
        )
    }
}