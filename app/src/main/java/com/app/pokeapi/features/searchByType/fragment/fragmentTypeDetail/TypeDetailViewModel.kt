package com.app.pokeapi.features.searchByType.fragment.fragmentTypeDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pokeapi.features.searchByType.fragment.fragmentTypeDetail.model.TypeDetailUIState
import com.app.pokeapi.repository.PokeApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TypeDetailViewModel
@Inject constructor(
    private val repository: PokeApiRepository
) : ViewModel() {

    // TODO Try to use channel and better state
    private var _uiState: MutableStateFlow<TypeDetailUIState> =
        MutableStateFlow(TypeDetailUIState.ShowLoader(true))
    val uiState get() = _uiState.asStateFlow()

    fun init(typeID: String) {
        getTypeDetail(typeID)
    }

    private fun getTypeDetail(typeID: String) {
        viewModelScope.launch {
            _uiState.update { TypeDetailUIState.ShowLoader(true) }

            repository.getTypeDetail(typeID)
                .onSuccess { result ->
                    _uiState.update { TypeDetailUIState.BindTypeDetail(result) }
                }.onFailure { error ->
                    _uiState.update {
                        TypeDetailUIState.ShowGenericError(
                            error = error.message,
                            onRetry = {
                                getTypeDetail(typeID)
                            }
                        )
                    }
                }

            _uiState.update { TypeDetailUIState.ShowLoader(false) }
        }
    }
}