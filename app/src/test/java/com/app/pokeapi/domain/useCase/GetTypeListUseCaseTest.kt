package com.app.pokeapi.domain.useCase

import com.app.pokeapi.data.entities.TypeEntity
import com.app.pokeapi.data.entities.TypeEntityList
import com.app.pokeapi.data.service.PokeApiService
import com.app.pokeapi.domain.useCase.getTypeList.GetTypeListUseCaseImp
import com.app.pokeapi.model.type.TypeEnum
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetTypeListUseCaseTest {

    @RelaxedMockK
    private lateinit var service: PokeApiService

    lateinit var getTypeListUseCase: GetTypeListUseCaseImp

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getTypeListUseCase = GetTypeListUseCaseImp(service)
    }

    @Test
    fun `when service returns null then get an empty list`() = runBlocking {
        // Given
        coEvery { service.getTypeList() } returns null

        // When
        val result = getTypeListUseCase()

        // Then
        coVerify(exactly = 1) { service.getTypeList() }
        assert(true) { result.isEmpty() }
    }

    @Test
    fun `when service returns something map values to domain`() = runBlocking {
        // Given
        val mockTypeList = TypeEntityList(
            list = listOf(
                TypeEntity(
                    type = "grass",
                    infoURL = "grass.com"
                ),
                TypeEntity(
                    type = "fire",
                    infoURL = "fire.com"
                ),
                TypeEntity(
                    type = "water",
                    infoURL = "water.com"
                ),
            )
        )
        coEvery { service.getTypeList() } returns mockTypeList

        // When
        val result = getTypeListUseCase()

        // Then
        coVerify(exactly = 1) { service.getTypeList() }
        assert(true) {
            !result.isNullOrEmpty() &&
                    result.size == 3 &&
                    result[0].typeName == mockTypeList.list[0].type &&
                    result[0].linkURL == mockTypeList.list[0].infoURL &&
                    result[0].type == TypeEnum.valueOf(mockTypeList.list[0].type.uppercase())
        }

    }

    @Test
    fun `when service returns something with no type equivalence at TypesEnum map values to model excluding those values`() =
        runBlocking {
            // Given
            val mockTypeList = TypeEntityList(
                list = listOf(
                    TypeEntity(
                        type = "grass",
                        infoURL = "grass.com"
                    ),
                    TypeEntity(
                        type = "noType",
                        infoURL = ""
                    )
                    ,
                    TypeEntity(
                        type = "water",
                        infoURL = "water.com"
                    ),
                )
            )
            coEvery { service.getTypeList() } returns mockTypeList

            // When
            val result = getTypeListUseCase()

            // Then
            coVerify(exactly = 1) { service.getTypeList() }
            assert(true) {
                !result.isNullOrEmpty() &&
                        result.size == 2 &&
                        result[1].typeName == mockTypeList.list[2].type &&
                        result[1].linkURL == mockTypeList.list[2].infoURL &&
                        result[1].type == TypeEnum.valueOf(mockTypeList.list[2].type.uppercase())
            }

        }
}