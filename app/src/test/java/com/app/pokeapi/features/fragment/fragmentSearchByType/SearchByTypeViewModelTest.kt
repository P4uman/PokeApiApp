package com.app.pokeapi.features.fragment.fragmentSearchByType

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.pokeapi.pokeapi.domain.useCase.getTypeList.model.TypeEnum
import com.app.pokeapi.domain.model.TypeModel
import com.app.pokeapi.domain.useCase.getTypeList.GetTypeListUseCaseImp
import com.app.pokeapi.features.searchByType.fragment.fragmentSearchByType.model.TypeDisplay
import com.app.pokeapi.features.searchByType.fragment.fragmentSearchByType.SearchByTypeViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchByTypeViewModelTest {

    @RelaxedMockK
    private lateinit var getTypeListUseCase: GetTypeListUseCaseImp


    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewModel is created retrieve type list`() {
        // Given
        val typeListModel = listOf(
            TypeModel(
                typeName = "Grass",
                linkURL = "Grass.com",
                type = TypeEnum.GRASS
            ),
            TypeModel(
                typeName = "Fire",
                linkURL = "Fire.com",
                type = TypeEnum.FIRE
            ),
            TypeModel(
                typeName = "Water",
                linkURL = "Water.com",
                type = TypeEnum.WATER
            )
        )
        coEvery { getTypeListUseCase() } returns typeListModel

        // When
        val viewModel = SearchByTypeViewModel(getTypeListUseCase)

        // Then
        val resources = TypeEnum.getTypeResource(TypeEnum.GRASS)
        assert(true) {
            viewModel.typeListDisplay.value != null &&
                    viewModel.typeListDisplay.value?.size == 3 &&
                    viewModel.typeListDisplay.value?.first() == TypeDisplay(
                name = TypeEnum.GRASS.name,
                color = resources.color,
                icon = resources.icon
            )
        }
    }
}