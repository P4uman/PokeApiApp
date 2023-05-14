package com.app.pokeapi.core.baseUI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragmentMVVM<V : ViewBinding> : Fragment() {

    protected val binding by lazy { inflateViewBinding(layoutInflater) }
    abstract fun inflateViewBinding(layoutInflater: LayoutInflater): V
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
        initObservers()
    }

    /**
     * Place here init views logic executed after onViewCreated
     */
    protected open fun initViews() {}

    /**
     *  Place here listeners of view components
     */
    protected open fun initListeners() {}

    /**
     * Place here observers to viewModels
     */
    protected open fun initObservers() {}

    protected fun showLoader(visible: Boolean) {
        if (activity is BaseActivityMVVM<*>) {
            (activity as BaseActivityMVVM<*>).showLoader(visible)
        }
    }
}