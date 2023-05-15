package com.app.pokeapi.ui.fragment.fragmentSearchByType.model

sealed class SearchByTypeUIState {

    data class ShowLoader(val visible: Boolean) : SearchByTypeUIState()

    data class ShowGenericError(val error: String?, val onRetry: () -> Unit) : SearchByTypeUIState()
    data class BindTypeList(val list: List<TypeDisplay>) : SearchByTypeUIState()
}