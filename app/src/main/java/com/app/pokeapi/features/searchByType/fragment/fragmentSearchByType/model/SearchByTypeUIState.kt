package com.app.pokeapi.features.searchByType.fragment.fragmentSearchByType.model

import com.app.pokeapi.model.type.TypeDisplay

sealed class SearchByTypeUIState {

    data class ShowLoader(val visible: Boolean) : SearchByTypeUIState()

    data class ShowGenericError(val error: String?, val onRetry: () -> Unit) : SearchByTypeUIState()
    data class BindTypeList(val list: List<TypeDisplay>) : SearchByTypeUIState()
}