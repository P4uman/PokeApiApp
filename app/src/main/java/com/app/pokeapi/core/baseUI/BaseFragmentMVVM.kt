package com.app.pokeapi.core.baseUI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.pokeapi.core.di.FragmentViewModule
import javax.inject.Inject

abstract class BaseFragmentMVVM<VIEW : BaseViewMVVM<*, *>> : Fragment() {

    @Inject
    lateinit var module: FragmentViewModule
    protected val mView: VIEW by lazy { injectView(layoutInflater) }

    /**
     * Inject view component inherited of VaseViewMVVM from FragmentViewModule
     * Place provider at FragmentViewModule
     */
    abstract fun injectView(layoutInflater: LayoutInflater): VIEW

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return mView.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initObservers()
    }

    /**
     * Place here init logic executed after onViewCreated
     */
    protected open fun init() {}
    /**
     * Place here observers to viewModels
     */
    protected open fun initObservers(){}
}