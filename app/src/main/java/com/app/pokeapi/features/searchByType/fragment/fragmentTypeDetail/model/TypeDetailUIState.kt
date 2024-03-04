package com.app.pokeapi.features.searchByType.fragment.fragmentTypeDetail.model

import com.app.pokeapi.model.PokemonShortModel

sealed class TypeDetailUIState {

    data class ShowLoader(val visible: Boolean) : TypeDetailUIState()
    data class ShowGenericError(val error: String?, val onRetry: () -> Unit) : TypeDetailUIState()
    data class BindTypeDetail(val display: List<PokemonShortModel>) : TypeDetailUIState()
}