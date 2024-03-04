package com.app.pokeapi.features.searchByType.fragment.fragmentSearchByType

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pokeapi.features.searchByType.fragment.fragmentSearchByType.model.SearchByTypeUIState
import com.app.pokeapi.useCase.getTypeList.GetTypeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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
            _uiState.update { SearchByTypeUIState.ShowLoader(true) }
            getTypeListUseCase.invoke()
                .onSuccess { result ->
                    _uiState.update {
                        SearchByTypeUIState.BindTypeList(result)
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        SearchByTypeUIState.ShowGenericError(error.message) {
                            getTypeListMenu()
                        }
                    }
                }
            _uiState.update { SearchByTypeUIState.ShowLoader(false) }
        }
    }
}