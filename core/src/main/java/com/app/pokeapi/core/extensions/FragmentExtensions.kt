package com.app.pokeapi.core.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow

fun <T> Fragment.collectState(
    flow: Flow<T>,
    collect: suspend (T) -> Unit
) {
    lifecycleScope.launchWhenStarted {
        flow.collect {
            collect(it)
        }
    }
}