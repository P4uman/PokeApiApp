package com.app.pokeapi.model.type

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
        fun fromString(value: String) = TypeEnum.values().find { it.toString().lowercase() == value.lowercase() }
    }
}
