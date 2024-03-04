package com.app.pokeapi.features.searchByType.fragment.fragmentSearchByType

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pokeapi.pokeapi.domain.model.TypeModel
import com.app.pokeapi.pokeapi.domain.useCase.getTypeList.GetTypeListUseCase
import com.app.pokeapi.pokeapi.domain.useCase.getTypeList.model.GetTypeListResult
import com.app.pokeapi.pokeapi.domain.useCase.getTypeList.model.TypeEnum
import com.app.pokeapi.features.searchByType.fragment.fragmentSearchByType.model.SearchByTypeUIState
import com.app.pokeapi.features.searchByType.fragment.fragmentSearchByType.model.TypeDisplay
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
                .onStart { _uiState.update { SearchByTypeUIState.ShowLoader(true) } }
                .onCompletion { _uiState.update { SearchByTypeUIState.ShowLoader(false) } }
                .catch { cause: Throwable ->
                    handleGetTypeListMenuError(cause.message)
                }
                .collect { result ->
                    when (result) {
                        is GetTypeListResult.OnFailure -> {
                            handleGetTypeListMenuError(result.failure.name)
                        }
                        is GetTypeListResult.OnSuccess -> {
                            _uiState.update {
                                SearchByTypeUIState.BindTypeList(mapToDisplay(result.list))
                            }
                        }
                    }
                }
        }
    }

    private fun handleGetTypeListMenuError(error: String?) {
        _uiState.update {
            SearchByTypeUIState.ShowGenericError(error) {
                getTypeListMenu()
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
}