package com.app.pokeapi.features.searchByType.fragment.fragmentSearchByType.model

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

data class TypeDisplay(
    val name: String,
    @ColorRes val color: Int,
    @DrawableRes val icon: Int
)