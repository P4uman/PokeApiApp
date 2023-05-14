package com.app.pokeapi.ui.fragment.fragmentTypeDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pokeapi.domain.model.TypeDetailModel
import com.app.pokeapi.domain.useCase.GetTypeDetailUseCase
import com.app.pokeapi.ui.fragment.fragmentSearchByType.model.SearchByTypeUIState
import com.app.pokeapi.ui.fragment.fragmentTypeDetail.model.PokemonShortDisplay
import com.app.pokeapi.ui.fragment.fragmentTypeDetail.model.TypeDetailDisplay
import com.app.pokeapi.ui.fragment.fragmentTypeDetail.model.TypeDetailUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TypeDetailViewModel
@Inject constructor(
    val getTypeDetailUseCase: GetTypeDetailUseCase
) : ViewModel() {

    private var _uiState: MutableStateFlow<TypeDetailUIState> =
        MutableStateFlow(TypeDetailUIState.ShowLoader(true))
    val uiState get() = _uiState.asStateFlow()

    fun init(typeID: String) {
        viewModelScope.launch {
            _uiState.update { TypeDetailUIState.ShowLoader(true) }
            val result =  mapToDisplay(getTypeDetailUseCase(typeID))
            _uiState.update { TypeDetailUIState.BindTypeDetail(result)  }
            _uiState.update { TypeDetailUIState.ShowLoader(false) }
        }
    }

    private fun mapToDisplay(model: TypeDetailModel) =
        TypeDetailDisplay(
            model.pokemonList.map { pokemonShortModel ->
                PokemonShortDisplay(pokemonShortModel.pokemonName)
            }
        )

}