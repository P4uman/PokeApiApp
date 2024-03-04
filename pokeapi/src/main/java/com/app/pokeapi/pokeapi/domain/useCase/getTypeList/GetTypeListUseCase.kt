package com.app.pokeapi.pokeapi.domain.useCase.getTypeList

import com.app.pokeapi.pokeapi.domain.useCase.getTypeList.model.GetTypeListResult
import kotlinx.coroutines.flow.Flow

interface GetTypeListUseCase {
    suspend fun invoke(): Flow<GetTypeListResult>
}