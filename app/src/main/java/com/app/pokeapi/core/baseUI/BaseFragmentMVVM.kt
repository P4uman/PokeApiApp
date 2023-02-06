package com.app.pokeapi.core.baseUI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragmentMVVM<VIEW : BaseViewMVVM<*, *>> : Fragment() {

    protected val myView: VIEW by lazy { getView(layoutInflater) }

    abstract fun getView(layoutInflater: LayoutInflater): VIEW

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return myView.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    protected open fun initObservers(){}
}