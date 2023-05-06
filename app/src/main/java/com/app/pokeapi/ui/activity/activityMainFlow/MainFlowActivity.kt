package com.app.pokeapi.ui.activity.activityMainFlow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import com.app.pokeapi.core.baseUI.BaseActivityMVVM
import com.app.pokeapi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFlowActivity: BaseActivityMVVM<ActivityMainBinding>() {
    private val loaderViewModel: LoaderViewModel by viewModels()

    override fun initObservers() {
        loaderViewModel.showLoading.observe(this) { loaderVisible ->
            showLoader(loaderVisible)
        }
    }

    private fun showLoader(visible: Boolean) {
        binding.llLoaderContainer.visibility =
            if (visible) {
                View.VISIBLE
            } else {
                View.GONE
            }
    }

    override fun inflateViewBinding(layoutInflater: LayoutInflater) =
        ActivityMainBinding.inflate(layoutInflater)
}