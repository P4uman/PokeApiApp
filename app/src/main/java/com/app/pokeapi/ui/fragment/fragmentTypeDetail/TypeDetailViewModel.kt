package com.app.pokeapi.ui.fragment.fragmentTypeDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pokeapi.pokeapi.domain.model.TypeDetailModel
import com.app.pokeapi.pokeapi.domain.useCase.getTypeDetail.GetTypeDetailUseCase
import com.app.pokeapi.pokeapi.domain.useCase.getTypeDetail.model.GetTypeDetailResult
import com.app.pokeapi.ui.fragment.fragmentTypeDetail.model.PokemonShortDisplay
import com.app.pokeapi.ui.fragment.fragmentTypeDetail.model.TypeDetailDisplay
import com.app.pokeapi.ui.fragment.fragmentTypeDetail.model.TypeDetailUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TypeDetailViewModel
@Inject constructor(
    private val getTypeDetailUseCase: GetTypeDetailUseCase
) : ViewModel() {

    private var _uiState: MutableStateFlow<TypeDetailUIState> =
        MutableStateFlow(TypeDetailUIState.ShowLoader(true))
    val uiState get() = _uiState.asStateFlow()

    fun init(typeID: String) {
        getTypeDetail(typeID)
    }

    private fun getTypeDetail(typeID: String) {
        viewModelScope.launch {
            getTypeDetailUseCase.invoke(typeID)
                .onStart {
                    _uiState.update { TypeDetailUIState.ShowLoader(true) }
                }
                .onCompletion {
                    _uiState.update { TypeDetailUIState.ShowLoader(false) }
                }
                .catch {
                    handleGetTypeDetailError(it.message, typeID)
                }
                .collect { result ->
                    when (result) {
                        is GetTypeDetailResult.OnFailure -> {
                            handleGetTypeDetailError(result.failure.name, typeID)
                        }
                        is GetTypeDetailResult.OnSuccess -> {
                            _uiState.update {
                                TypeDetailUIState.BindTypeDetail(mapToDisplay(result.result))
                            }
                        }
                    }
                }
        }
    }

    private fun handleGetTypeDetailError(error: String?, typeID: String) {
        _uiState.update {
            TypeDetailUIState.ShowGenericError(error) {
                getTypeDetail(typeID)
            }
        }
    }

    private fun mapToDisplay(model: TypeDetailModel) =
        TypeDetailDisplay(
            model.pokemonList.map { pokemonShortModel ->
                PokemonShortDisplay(pokemonShortModel.pokemonName)
            }
        )

}