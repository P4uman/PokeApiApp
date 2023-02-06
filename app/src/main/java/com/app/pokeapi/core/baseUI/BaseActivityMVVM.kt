package com.app.pokeapi.core.baseUI

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivityMVVM<VIEW : BaseViewMVVM<*, *>> : AppCompatActivity(){

    protected val view: VIEW by lazy { getView(layoutInflater) }

    abstract fun getView(layoutInflater: LayoutInflater): VIEW
    abstract fun initObservers()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.rootView)
        initObservers()
    }
}
