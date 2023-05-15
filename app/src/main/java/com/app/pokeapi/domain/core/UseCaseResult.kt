package com.app.pokeapi.domain.core

sealed class UseCaseResult {

    data class OnSuccess<T: BaseModel>(val result: T) : UseCaseResult()
    data class OnFailure(val failure: FailureTypes) : UseCaseResult()
}