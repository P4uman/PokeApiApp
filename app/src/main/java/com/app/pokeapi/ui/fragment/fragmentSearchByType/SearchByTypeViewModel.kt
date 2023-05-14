package com.app.pokeapi.ui.fragment.fragmentSearchByType

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pokeapi.core.TypeEnum
import com.app.pokeapi.domain.model.TypeModel
import com.app.pokeapi.domain.useCase.getTypeList.GetTypeListUseCase
import com.app.pokeapi.domain.useCase.getTypeList.model.GetTypeListResult
import com.app.pokeapi.ui.fragment.fragmentSearchByType.model.SearchByTypeUIState
import com.app.pokeapi.ui.fragment.fragmentSearchByType.model.TypeDisplay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchByTypeViewModel
@Inject constructor(
    private val getTypeListUseCase: GetTypeListUseCase
) : ViewModel() {

    private var _uiState: MutableStateFlow<SearchByTypeUIState> =
        MutableStateFlow(SearchByTypeUIState.ShowLoader(true))
    val uiState get() = _uiState.asStateFlow()

    init {
        getTypeListMenu()
    }

    private fun getTypeListMenu() {
        viewModelScope.launch {
            getTypeListUseCase.invoke()
                .onStart { _uiState.update {  SearchByTypeUIState.ShowLoader(true) }}
                .onCompletion { _uiState.update {  SearchByTypeUIState.ShowLoader(false) }}
                .catch { cause: Throwable ->
                    // TODO ON ERROR
                }
                .collect { result ->
                    when (result) {
                        is GetTypeListResult.OnFailure -> TODO()
                        is GetTypeListResult.OnSuccess -> {
                            _uiState.update {
                                SearchByTypeUIState.BindTypeList(mapToDisplay(result.list))
                            }
                        }
                    }
                }
        }
    }
}

private fun mapToDisplay(model: List<TypeModel>) = model.map { typeModel ->
    val typeResources = TypeEnum.getTypeResource(typeModel.type)
    TypeDisplay(
        name = typeModel.typeName,
        color = typeResources.color,
        icon = typeResources.icon
    )
}