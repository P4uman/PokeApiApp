package com.app.pokeapi.pokeapi.domain.useCase.getTypeList.model

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.app.pokeapi.pokeapi.R

enum class TypeEnum {
    NORMAL,
    FIGHTING,
    FLYING,
    POISON,
    GROUND,
    ROCK,
    BUG,
    GHOST,
    STEEL,
    FIRE,
    WATER,
    GRASS,
    ELECTRIC,
    PSYCHIC,
    ICE,
    DRAGON,
    DARK,
    FAIRY;

    companion object {
        fun getTypeResource(type: TypeEnum)
                : TypeResources =
            when (type) {
                NORMAL -> TypeResources(
                    color = R.color.type_normal,
                    icon = R.drawable.ic_normal
                )
                FIGHTING -> TypeResources(
                    color = R.color.type_fighting,
                    icon = R.drawable.ic_fighting
                )
                FLYING -> TypeResources(
                    color = R.color.type_flying,
                    icon = R.drawable.ic_flying
                )
                POISON -> TypeResources(
                    color = R.color.type_poison,
                    icon = R.drawable.ic_poison
                )
                GROUND -> TypeResources(
                    color = R.color.type_ground,
                    icon = R.drawable.ic_ground
                )
                ROCK -> TypeResources(
                    color = R.color.type_rock,
                    icon = R.drawable.ic_rock
                )
                BUG -> TypeResources(
                    color = R.color.type_bug,
                    icon = R.drawable.ic_bug
                )
                GHOST -> TypeResources(
                    color = R.color.type_ghost,
                    icon = R.drawable.ic_ghost
                )
                STEEL -> TypeResources(
                    color = R.color.type_steel,
                    icon = R.drawable.ic_steel
                )
                FIRE -> TypeResources(
                    color = R.color.type_fire,
                    icon = R.drawable.ic_fire
                )
                WATER -> TypeResources(
                    color = R.color.type_water,
                    icon = R.drawable.ic_water
                )
                GRASS -> TypeResources(
                    color = R.color.type_grass,
                    icon = R.drawable.ic_grass
                )
                ELECTRIC -> TypeResources(
                    color = R.color.type_electric,
                    icon = R.drawable.ic_electric
                )
                PSYCHIC -> TypeResources(
                    color = R.color.type_psychic,
                    icon = R.drawable.ic_psychic
                )
                ICE -> TypeResources(
                    color = R.color.type_ice,
                    icon = R.drawable.ic_ice
                )
                DRAGON -> TypeResources(
                    color = R.color.type_dragon,
                    icon = R.drawable.ic_dragon
                )
                DARK -> TypeResources(
                    color = R.color.type_dark,
                    icon = R.drawable.ic_dark
                )
                FAIRY -> TypeResources(
                    color = R.color.type_fairy,
                    icon = R.drawable.ic_fairy
                )
            }
    }
}

data class TypeResources(
    @ColorRes val color: Int,
    @DrawableRes val icon: Int
)