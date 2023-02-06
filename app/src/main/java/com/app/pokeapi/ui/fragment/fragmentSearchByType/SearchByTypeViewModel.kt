package com.app.pokeapi.ui.fragment.fragmentSearchByType

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pokeapi.core.TypeEnum
import com.app.pokeapi.domain.useCase.GetTypeListUseCase
import com.app.pokeapi.ui.display.TypeDisplay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchByTypeViewModel
@Inject constructor(
    private val getTypeListUseCase: GetTypeListUseCase
) : ViewModel() {
    val typeListModel = MutableLiveData<List<TypeDisplay>>()
    val showLoader = MutableLiveData<Boolean>()

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