package com.app.pokeapi.domain.useCase

import com.app.pokeapi.core.TypeEnum
import com.app.pokeapi.data.network.TypesService
import com.app.pokeapi.data.network.entities.TypeEntity
import com.app.pokeapi.domain.model.TypeModel

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
        // Filter Types not registered at TypeEnum
        return try {
            TypeModel(
                typeName = entity.type,
                linkURL = entity.infoURL,
                type = TypeEnum.valueOf(entity.type.uppercase())
            )
        } catch (e: Exception) {
            null
        }
    }
}