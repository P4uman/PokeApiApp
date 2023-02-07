package com.app.pokeapi.core.baseUI

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.app.pokeapi.core.di.ActivityViewModule
import javax.inject.Inject

abstract class BaseActivityMVVM<VIEW : BaseViewMVVM<*, *>> : AppCompatActivity(){

    @Inject
    lateinit var module: ActivityViewModule
    protected val myView: VIEW by lazy { injectView(layoutInflater) }

    /**
     * Inject view component inherited of VaseViewMVVM from ActivityViewModule
     * Place provider at ActivityViewModule
     */
    abstract fun injectView(layoutInflater: LayoutInflater): VIEW

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(myView.rootView)
        initObservers()
    }

    /**
     * Place here observers to viewModels
     */
    protected open fun initObservers(){}
}
