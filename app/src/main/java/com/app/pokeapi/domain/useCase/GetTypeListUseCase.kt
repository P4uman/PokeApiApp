package com.app.pokeapi.domain.useCase

import com.app.pokeapi.core.extractIDFromURl
import com.app.pokeapi.data.network.TypesService
import com.app.pokeapi.data.network.entities.TypeEntity
import com.app.pokeapi.domain.model.TypeModel

private const val IGNORE_ID_MAX = 1000

class GetTypeListUseCase {

    private val service = TypesService()

    suspend operator fun invoke(): List<TypeModel> {
        val modelList: List<TypeModel?> =
            service.getTypeList()?.list?.map { typeEntity ->
                mapToDomain(typeEntity)
            } ?: emptyList()

        return modelList.filterNotNull()
    }

    private fun mapToDomain(
        entity: TypeEntity
    ): TypeModel? {
        // Lets ignore IDs greater than 1000 because these types are not basic pokémon types
        val id = extractIDFromURl(entity.infoURL) ?: IGNORE_ID_MAX
        return if (id < IGNORE_ID_MAX) {
            return TypeModel(
                typeID = 0,
                typeName = entity.type,
                linkURL = entity.infoURL
            )
        } else {
            null
        }

    }
}