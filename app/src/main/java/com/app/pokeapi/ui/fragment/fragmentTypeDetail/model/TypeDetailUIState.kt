package com.app.pokeapi.ui.fragment.fragmentTypeDetail.model

sealed class TypeDetailUIState {

    data class ShowLoader(val visible: Boolean) : TypeDetailUIState()
    data class ShowGenericError(val error: String?, val onRetry: () -> Unit) : TypeDetailUIState()
    data class BindTypeDetail(val display: TypeDetailDisplay) : TypeDetailUIState()
}