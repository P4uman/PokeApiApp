package com.app.pokeapi.core.baseUI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<V : ViewBinding> : Fragment() {

    protected val binding by lazy { inflateViewBinding(layoutInflater) }
    abstract fun inflateViewBinding(layoutInflater: LayoutInflater): V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initUIState()
    }
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
     * Handle here collectState logic, it runs at onCreate
     */
    protected open fun initUIState() {}
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
        if (activity is BaseActivity<*>) {
            (activity as BaseActivity<*>).showLoader(visible)
        }
    }
}