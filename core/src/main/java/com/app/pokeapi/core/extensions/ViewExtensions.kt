package com.app.pokeapi.core.extensions

import android.view.View

fun View.visible(visibility: Boolean) {
    if (visibility)  {
        this.show()
    }else {
        this.hide()
    }
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}