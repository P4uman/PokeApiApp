package com.app.pokeapi.domain.useCase

import com.app.pokeapi.data.entities.PokemonShortEntity
import com.app.pokeapi.data.entities.PokemonShortInnerEntity
import com.app.pokeapi.data.entities.TypeDetailEntity
import com.app.pokeapi.useCase.getTypeDetail.GetTypeDetailUseCase
import com.app.pokeapi.useCase.getTypeDetail.GetTypeDetailUseCaseImp
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetTypeDetailUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: com.app.pokeapi.repository.PokeApiRepository

    lateinit var getTypeListUseCase: com.app.pokeapi.useCase.getTypeDetail.GetTypeDetailUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getTypeListUseCase =
            com.app.pokeapi.useCase.getTypeDetail.GetTypeDetailUseCaseImp(repository)
    }

    @Test
    fun `when service returns null then get an empty list`() = runBlocking {
        val typeID = "typeID"
        // Given
        coEvery { repository.getTypeDetail(typeID) } returns null

        // When
        val result = getTypeListUseCase.invoke(typeID)

        // Then
        coVerify(exactly = 1) { repository.getTypeDetail(typeID) }
        assert(true) { result. }
    }

    @Test
    fun `when service returns anything then map values to domain`() = runBlocking {
        // Given
        val typeID = "typeID"
        val typeEntity = com.app.pokeapi.data.entities.TypeDetailEntity(
            ID = 1,
            pokemonList = listOf(
                com.app.pokeapi.data.entities.PokemonShortEntity(
                    com.app.pokeapi.data.entities.PokemonShortInnerEntity(
                        name = "name1"
                    )
                ),
                com.app.pokeapi.data.entities.PokemonShortEntity(
                    com.app.pokeapi.data.entities.PokemonShortInnerEntity(
                        name = "name2"
                    )
                ),
                com.app.pokeapi.data.entities.PokemonShortEntity(
                    com.app.pokeapi.data.entities.PokemonShortInnerEntity(
                        name = "name3"
                    )
                )
            )
        )
        coEvery { service.getTypeDetail(typeID) } returns typeEntity

        // When
        val result = getTypeListUseCase(typeID)

        // Then
        coVerify(exactly = 1) { service.getTypeDetail(typeID) }
        assert(true) {
            result.pokemonList.isNotEmpty() &&
                    result.pokemonList.size == 3 &&
                    result.pokemonList[0].pokemonName == typeEntity.pokemonList[0].pokemon.name &&
                    result.pokemonList[2].pokemonName == typeEntity.pokemonList[2].pokemon.name
        }
    }

}