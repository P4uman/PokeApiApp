package com.app.pokeapi.domain.useCase

import com.app.pokeapi.data.network.TypeDetailService
import com.app.pokeapi.data.network.entities.PokemonShortEntity
import com.app.pokeapi.data.network.entities.PokemonShortInnerEntity
import com.app.pokeapi.data.network.entities.TypeDetailEntity
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetTypeDetailUseCaseTest {

    @RelaxedMockK
    private lateinit var service: TypeDetailService

    lateinit var getTypeListUseCase: GetTypeDetailUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getTypeListUseCase = GetTypeDetailUseCase(service)
    }

    @Test
    fun `when service returns null then get an empty list`() = runBlocking {
        val typeID = "typeID"
        // Given
        coEvery { service.getTypeDetail(typeID) } returns null

        // When
        val result = getTypeListUseCase(typeID)

        // Then
        coVerify(exactly = 1) { service.getTypeDetail(typeID) }
        assert(true) { result.pokemonList.isEmpty() }
    }

    @Test
    fun `when service returns anything then map values to domain`() = runBlocking {
        // Given
        val typeID = "typeID"
        val typeEntity = TypeDetailEntity(
            ID = 1,
            pokemonList = listOf(
                PokemonShortEntity(
                    PokemonShortInnerEntity(
                        name = "name1"
                    )
                ),
                PokemonShortEntity(
                    PokemonShortInnerEntity(
                        name = "name2"
                    )
                ),
                PokemonShortEntity(
                    PokemonShortInnerEntity(
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