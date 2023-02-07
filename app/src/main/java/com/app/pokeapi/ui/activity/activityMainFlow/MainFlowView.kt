package com.app.pokeapi.ui.activity.activityMainFlow

import android.view.LayoutInflater
import android.view.View
import com.app.pokeapi.core.baseUI.BaseViewMVVM
import com.app.pokeapi.databinding.ActivityMainBinding

class MainFlowView(
    layoutInflater: LayoutInflater
) : BaseViewMVVM<ActivityMainBinding, BaseViewMVVM.BaseViewListeners>(layoutInflater) {

    override fun inflateViewBinding(layoutInflater: LayoutInflater) =
        ActivityMainBinding.inflate(layoutInflater)

    fun showLoader(visible: Boolean) {
        binding.llLoaderContainer.visibility =
            if (visible) {
                View.VISIBLE
            } else {
                View.GONE
            }
    }

}