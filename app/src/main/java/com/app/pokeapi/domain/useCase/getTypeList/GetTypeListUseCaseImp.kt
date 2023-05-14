package com.app.pokeapi.domain.useCase.getTypeList

import com.app.pokeapi.core.TypeEnum
import com.app.pokeapi.data.entities.TypeEntity
import com.app.pokeapi.domain.core.FailureTypes
import com.app.pokeapi.domain.model.TypeModel
import com.app.pokeapi.domain.repository.PokeApiRepository
import com.app.pokeapi.domain.useCase.getTypeList.model.GetTypeListResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTypeListUseCaseImp
@Inject constructor(
    private val repository: PokeApiRepository
): GetTypeListUseCase{

    override suspend fun invoke(): Flow<GetTypeListResult> = getTypeList()

    private suspend fun getTypeList() : Flow<GetTypeListResult> = flow {
        runCatching {
            repository.getTypeList()
        }.map {entityList ->
            val modelList : List<TypeModel?> = entityList?.list?.map {entity ->
                mapToDomain(entity)
            } ?: emptyList()

            val result = modelList.filterNotNull()
            if (result.isNotEmpty()) {
                emit(GetTypeListResult.OnSuccess(result))
            } else {
                emit (GetTypeListResult.OnFailure(FailureTypes.Empty))
            }
        }.getOrElse {
            emit(GetTypeListResult.OnFailure(FailureTypes.GeneralError))
        }
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