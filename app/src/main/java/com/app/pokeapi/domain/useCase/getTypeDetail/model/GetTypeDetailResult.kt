package com.app.pokeapi.domain.useCase.getTypeDetail.model

import com.app.pokeapi.domain.core.FailureTypes
import com.app.pokeapi.domain.model.TypeDetailModel
import com.app.pokeapi.domain.model.TypeModel

sealed class GetTypeDetailResult {

    data class OnSuccess(val result: TypeDetailModel ): GetTypeDetailResult()
    data class OnFailure(val failure: FailureTypes) : GetTypeDetailResult()
}