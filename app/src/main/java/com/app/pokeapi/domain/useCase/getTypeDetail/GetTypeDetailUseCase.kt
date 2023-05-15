package com.app.pokeapi.domain.useCase.getTypeDetail

import com.app.pokeapi.domain.useCase.getTypeDetail.model.GetTypeDetailResult
import kotlinx.coroutines.flow.Flow

interface GetTypeDetailUseCase {

     suspend fun invoke(typeID: String): Flow<GetTypeDetailResult>
}