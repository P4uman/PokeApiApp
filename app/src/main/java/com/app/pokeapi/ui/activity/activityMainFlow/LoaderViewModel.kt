package com.app.pokeapi.ui.activity.activityMainFlow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoaderViewModel: ViewModel() {

    val showLoading = MutableLiveData<Boolean>()

    fun showLoading(visible: Boolean) {
        showLoading.postValue(visible)
    }
}