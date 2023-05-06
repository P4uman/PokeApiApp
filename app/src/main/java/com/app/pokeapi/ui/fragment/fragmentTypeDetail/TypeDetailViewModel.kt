package com.app.pokeapi.ui.fragment.fragmentTypeDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pokeapi.domain.model.TypeDetailModel
import com.app.pokeapi.domain.useCase.GetTypeDetailUseCase
import com.app.pokeapi.ui.fragment.fragmentTypeDetail.model.PokemonShortDisplay
import com.app.pokeapi.ui.fragment.fragmentTypeDetail.model.TypeDetailDisplay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TypeDetailViewModel
@Inject constructor(
    val getTypeDetailUseCase: GetTypeDetailUseCase
) : ViewModel() {

    val typeDetail = MutableLiveData<TypeDetailDisplay>()
    val showLoading = MutableLiveData<Boolean>()

    fun init(typeID: String) {
        viewModelScope.launch {
            showLoading.postValue(true)
            typeDetail.postValue(
                mapToDisplay(
                    getTypeDetailUseCase(typeID)
                )
            )
            showLoading.postValue(false)
        }
    }

    private fun mapToDisplay(model: TypeDetailModel) =
        TypeDetailDisplay(
            model.pokemonList.map { pokemonShortModel ->
                PokemonShortDisplay(pokemonShortModel.pokemonName)
            }
        )

}