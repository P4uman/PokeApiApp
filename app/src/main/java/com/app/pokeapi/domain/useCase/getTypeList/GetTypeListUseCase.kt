package com.app.pokeapi.domain.useCase.getTypeList

import com.app.pokeapi.domain.core.UseCaseResult
import com.app.pokeapi.domain.useCase.getTypeList.model.GetTypeListResult
import kotlinx.coroutines.flow.Flow

interface GetTypeListUseCase {
    suspend fun invoke(): Flow<UseCaseResult>
}