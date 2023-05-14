package com.app.pokeapi.core.baseUI

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivityMVVM<V: ViewBinding> : AppCompatActivity(){

    protected val binding by lazy { inflateViewBinding(layoutInflater) }
    abstract fun inflateViewBinding(layoutInflater: LayoutInflater): V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initObservers()
    }

    /**
     * Place here observers to viewModels
     */
    protected open fun initObservers(){}

    internal open fun showLoader(visible: Boolean) {}
}
