package com.app.pokeapi.ui.activity.activityMainFlow

import android.view.LayoutInflater
import com.app.pokeapi.core.baseUI.BaseActivity
import com.app.pokeapi.core.extensions.hide
import com.app.pokeapi.core.extensions.show
import com.app.pokeapi.core.extensions.visible
import com.app.pokeapi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFlowActivity : BaseActivity<ActivityMainBinding>() {

    override fun showLoader(visible: Boolean) {
        binding.llLoaderContainer.visible(visible)
    }

    override fun showGenericError(error: String?, onRetry: () -> Unit) {
        super.showGenericError(error, onRetry)
        binding.llErrorContainer.show()
        binding.txtError.text = error
        binding.btnRetry.setOnClickListener {
            onRetry.invoke()
            hideGenericError()
        }
    }

    override fun hideGenericError() {
        super.hideGenericError()
        binding.llErrorContainer.hide()
    }
    override fun inflateViewBinding(layoutInflater: LayoutInflater) =
        ActivityMainBinding.inflate(layoutInflater)
}