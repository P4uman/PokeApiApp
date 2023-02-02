package com.app.pokeapi

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pokeapi.domain.model.TypeModel
import com.app.pokeapi.domain.useCase.GetTypeListUseCase
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val typeListModel = MutableLiveData<List<TypeModel>>()

    private val getTypeListUseCase = GetTypeListUseCase()

    fun init() {
        viewModelScope.launch {
            val result = getTypeListUseCase()
            typeListModel.postValue(result)
        }
    }
}