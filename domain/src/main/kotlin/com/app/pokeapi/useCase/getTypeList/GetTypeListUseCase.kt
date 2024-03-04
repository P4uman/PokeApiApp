package com.app.pokeapi.useCase.getTypeList

import com.app.pokeapi.model.type.TypeDisplay

interface GetTypeListUseCase {
    suspend fun invoke(): Result<List<TypeDisplay>>
}