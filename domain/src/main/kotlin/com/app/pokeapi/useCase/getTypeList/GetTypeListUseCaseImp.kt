package com.app.pokeapi.useCase.getTypeList

import com.app.pokeapi.model.type.TypeDisplay
import com.app.pokeapi.model.type.TypeEnum
import com.app.pokeapi.model.type.TypeResources
import com.app.pokeapi.repository.PokeApiRepository
import com.app.pokeapi.ui.R
import javax.inject.Inject

class GetTypeListUseCaseImp
@Inject constructor(
    private val repository: PokeApiRepository
) : GetTypeListUseCase {
    override suspend fun invoke(): Result<List<TypeDisplay>> {
        return repository.getTypeList().map { typeModeList ->
            typeModeList.map {
                TypeDisplay(
                    name = it.name,
                    linkURL = it.infoURL,
                    type = it.type,
                    resources = getResourcesFromType(it.type)
                )
            }
        }
    }

    private fun getResourcesFromType(type: TypeEnum) = when (type) {
        TypeEnum.NORMAL -> TypeResources(
            color = R.color.type_normal,
            icon = R.drawable.ic_normal
        )

        TypeEnum.FIGHTING -> TypeResources(
            color = R.color.type_fighting,
            icon = R.drawable.ic_fighting
        )

        TypeEnum.FLYING -> TypeResources(
            color = R.color.type_flying,
            icon = R.drawable.ic_flying
        )

        TypeEnum.POISON -> TypeResources(
            color = R.color.type_poison,
            icon = R.drawable.ic_poison
        )

        TypeEnum.GROUND -> TypeResources(
            color = R.color.type_ground,
            icon = R.drawable.ic_ground
        )

        TypeEnum.ROCK -> TypeResources(
            color = R.color.type_rock,
            icon = R.drawable.ic_rock
        )

        TypeEnum.BUG -> TypeResources(
            color = R.color.type_bug,
            icon = R.drawable.ic_bug
        )

        TypeEnum.GHOST -> TypeResources(
            color = R.color.type_ghost,
            icon = R.drawable.ic_ghost
        )

        TypeEnum.STEEL -> TypeResources(
            color = R.color.type_steel,
            icon = R.drawable.ic_steel
        )

        TypeEnum.FIRE -> TypeResources(
            color = R.color.type_fire,
            icon = R.drawable.ic_fire
        )

        TypeEnum.WATER -> TypeResources(
            color = R.color.type_water,
            icon = R.drawable.ic_water
        )

        TypeEnum.GRASS -> TypeResources(
            color = R.color.type_grass,
            icon = R.drawable.ic_grass
        )

        TypeEnum.ELECTRIC -> TypeResources(
            color = R.color.type_electric,
            icon = R.drawable.ic_electric
        )

        TypeEnum.PSYCHIC -> TypeResources(
            color = R.color.type_psychic,
            icon = R.drawable.ic_psychic
        )

        TypeEnum.ICE -> TypeResources(
            color = R.color.type_ice,
            icon = R.drawable.ic_ice
        )

        TypeEnum.DRAGON -> TypeResources(
            color = R.color.type_dragon,
            icon = R.drawable.ic_dragon
        )

        TypeEnum.DARK -> TypeResources(
            color = R.color.type_dark,
            icon = R.drawable.ic_dark
        )

        TypeEnum.FAIRY -> TypeResources(
            color = R.color.type_fairy,
            icon = R.drawable.ic_fairy
        )
    }
}