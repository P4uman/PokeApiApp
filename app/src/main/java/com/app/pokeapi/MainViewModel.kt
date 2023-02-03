package com.app.pokeapi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pokeapi.core.TypeEnum
import com.app.pokeapi.domain.useCase.GetTypeListUseCase
import com.app.pokeapi.ui.TypeDisplay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val typeListModel = MutableLiveData<List<TypeDisplay>>()

    private val getTypeListUseCase = GetTypeListUseCase()

    fun init() {
        viewModelScope.launch {
            val result = getTypeListUseCase()

            val displayList: List<TypeDisplay?> = result.map { typeModel ->
                val typeResources = TypeEnum.getTypeResource(typeModel.type)
                TypeDisplay(
                    name = typeModel.typeName,
                    color = typeResources.color,
                    icon = typeResources.icon
                )
            }
            typeListModel.postValue(displayList.filterNotNull())
        }
    }
}