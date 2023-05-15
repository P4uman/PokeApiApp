package com.app.pokeapi.pokeapi.domain.useCase.getTypeDetail.model

import com.app.pokeapi.pokeapi.domain.core.FailureTypes
import com.app.pokeapi.pokeapi.domain.model.TypeDetailModel


sealed class GetTypeDetailResult {

    data class OnSuccess(val result: TypeDetailModel): GetTypeDetailResult()
    data class OnFailure(val failure: FailureTypes) : GetTypeDetailResult()
}