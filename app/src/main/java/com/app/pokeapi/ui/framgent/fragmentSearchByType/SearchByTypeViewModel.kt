package com.app.pokeapi.ui.framgent.fragmentSearchByType

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pokeapi.core.TypeEnum
import com.app.pokeapi.domain.useCase.GetTypeListUseCase
import com.app.pokeapi.ui.display.TypeDisplay
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchByTypeViewModel : ViewModel() {
    val typeListModel = MutableLiveData<List<TypeDisplay>>()
    val showLoader = MutableLiveData<Boolean>()

    private val getTypeListUseCase = GetTypeListUseCase()

    init {
        viewModelScope.launch {
            showLoader.postValue(true)
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
            showLoader.postValue(false)
        }
    }
}