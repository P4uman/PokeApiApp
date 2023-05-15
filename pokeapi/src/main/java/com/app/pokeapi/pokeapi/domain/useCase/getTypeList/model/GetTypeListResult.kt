package com.app.pokeapi.pokeapi.domain.useCase.getTypeList.model

import com.app.pokeapi.pokeapi.domain.core.FailureTypes
import com.app.pokeapi.pokeapi.domain.model.TypeModel

sealed class GetTypeListResult {
    data class OnSuccess(val list: List<TypeModel>) : GetTypeListResult()
    data class OnFailure(val failure: FailureTypes) : GetTypeListResult()
}
