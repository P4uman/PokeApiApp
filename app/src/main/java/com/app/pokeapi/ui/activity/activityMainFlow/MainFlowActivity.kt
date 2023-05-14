package com.app.pokeapi.ui.activity.activityMainFlow

import android.view.LayoutInflater
import android.view.View
import com.app.pokeapi.core.baseUI.BaseActivity
import com.app.pokeapi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFlowActivity : BaseActivity<ActivityMainBinding>() {

    override fun showLoader(visible: Boolean) {
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