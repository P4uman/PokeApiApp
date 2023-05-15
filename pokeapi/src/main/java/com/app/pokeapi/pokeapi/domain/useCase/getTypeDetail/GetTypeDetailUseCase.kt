package com.app.pokeapi.pokeapi.domain.useCase.getTypeDetail

import com.app.pokeapi.pokeapi.domain.useCase.getTypeDetail.model.GetTypeDetailResult
import kotlinx.coroutines.flow.Flow

interface GetTypeDetailUseCase {

     suspend fun invoke(typeID: String): Flow<GetTypeDetailResult>
}