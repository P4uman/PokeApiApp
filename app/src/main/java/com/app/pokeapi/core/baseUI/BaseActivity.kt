package com.app.pokeapi.core.baseUI

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<V: ViewBinding> : AppCompatActivity(){

    protected val binding by lazy { inflateViewBinding(layoutInflater) }
    abstract fun inflateViewBinding(layoutInflater: LayoutInflater): V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    internal open fun showLoader(visible: Boolean) {}

    internal open fun showGenericError(error: String?, onRetry: () -> Unit) {}
    internal open fun hideGenericError() {}
}
