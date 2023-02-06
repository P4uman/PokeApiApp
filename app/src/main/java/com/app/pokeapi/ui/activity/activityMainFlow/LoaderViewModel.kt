package com.app.pokeapi.ui.activity.activityMainFlow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoaderViewModel
@Inject constructor() : ViewModel() {

    val showLoading = MutableLiveData<Boolean>()

    fun showLoading(visible: Boolean) {
        showLoading.postValue(visible)
    }
}