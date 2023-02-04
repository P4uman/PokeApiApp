package com.app.pokeapi.core.baseUI

import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding

abstract class BaseViewMVVM<V : ViewBinding, LISTENER_TYPE>(
    layoutInflater: LayoutInflater
) {

    interface BaseViewListeners

    protected val binding by lazy { getViewBinding(layoutInflater) }
    protected val listeners = HashSet<LISTENER_TYPE>()

    val  rootView: View by lazy { binding.root }

    abstract fun getViewBinding(layoutInflater: LayoutInflater): V

    fun registerListener(listener: LISTENER_TYPE) {
        listeners.add(listener)
    }

    fun unregisterListener(listener: LISTENER_TYPE) {
        listeners.remove(listener)
    }
}