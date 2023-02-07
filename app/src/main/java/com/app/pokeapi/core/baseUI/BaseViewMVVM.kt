package com.app.pokeapi.core.baseUI

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding

abstract class BaseViewMVVM<V : ViewBinding, LISTENER_TYPE>(
    layoutInflater: LayoutInflater
) {

    interface BaseViewListeners

    protected val binding by lazy { inflateViewBinding(layoutInflater) }
    protected val listeners = HashSet<LISTENER_TYPE>()
    protected val context: Context get() = binding.root.context

    val  rootView: View by lazy { binding.root }

    abstract fun inflateViewBinding(layoutInflater: LayoutInflater): V

    fun registerListener(listener: LISTENER_TYPE) {
        listeners.add(listener)
    }

    fun unregisterListener(listener: LISTENER_TYPE) {
        listeners.remove(listener)
    }
}