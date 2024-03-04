package com.app.pokeapi.model.type

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

data class TypeDisplay(
    val name: String,
    val linkURL: String,
    val type: TypeEnum,
    val resources: TypeResources
)

data class TypeResources(
    @ColorRes val color: Int,
    @DrawableRes val icon: Int
)