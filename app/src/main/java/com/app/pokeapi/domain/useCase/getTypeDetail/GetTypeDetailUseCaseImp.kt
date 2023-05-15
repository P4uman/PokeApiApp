package com.app.pokeapi.domain.useCase.getTypeDetail

import com.app.pokeapi.data.entities.TypeDetailEntity
import com.app.pokeapi.domain.core.FailureTypes
import com.app.pokeapi.domain.model.PokemonShortModel
import com.app.pokeapi.domain.model.TypeDetailModel
import com.app.pokeapi.domain.repository.PokeApiRepository
import com.app.pokeapi.domain.useCase.getTypeDetail.model.GetTypeDetailResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTypeDetailUseCaseImp
@Inject constructor(
    private val repository: PokeApiRepository
) : GetTypeDetailUseCase {

    override suspend fun invoke(typeID: String): Flow<GetTypeDetailResult> = getTypeDetail(typeID)

    private fun getTypeDetail(typeID: String): Flow<GetTypeDetailResult> = flow {
        runCatching {
            repository.getTypeDetail(typeID)
        }.map { entity ->
            if (entity != null) {
                emit(GetTypeDetailResult.OnSuccess(mapToDomain(entity)))
            } else {
                emit(GetTypeDetailResult.OnFailure(FailureTypes.Empty))
            }
        }.getOrElse {
            emit(GetTypeDetailResult.OnFailure(FailureTypes.GeneralError))
        }
    }

    private fun mapToDomain(
        entity: TypeDetailEntity
    ): TypeDetailModel {
        return TypeDetailModel(
            pokemonList = entity.pokemonList.map { pokemonShortEntity ->
                PokemonShortModel(pokemonShortEntity.pokemon.name)
            }
        )
    }


}