package com.app.pokeapi.ui.activity.activityMainFlow

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.app.pokeapi.core.baseUI.BaseActivityMVVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFlowActivity : BaseActivityMVVM<MainFlowView>() {

    private val loaderViewModel: LoaderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(myView.rootView)
    }

    override fun getView(layoutInflater: LayoutInflater) = MainFlowView(layoutInflater)

    override fun initObservers() {
        loaderViewModel.showLoading.observe(this) { loaderVisible ->
            myView.showLoader(loaderVisible)
        }
    }
}