package com.app.pokeapi.ui.fragment.fragmentSearchByType

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.app.pokeapi.core.baseUI.BaseFragment
import com.app.pokeapi.core.extensions.collectState
import com.app.pokeapi.databinding.FragmentSearchByTypeBinding
import com.app.pokeapi.ui.fragment.fragmentSearchByType.adapter.TypesMenuAdapter
import com.app.pokeapi.ui.fragment.fragmentSearchByType.model.SearchByTypeUIState
import com.app.pokeapi.ui.fragment.fragmentSearchByType.model.TypeDisplay
import dagger.hilt.android.AndroidEntryPoint

private const val GRID_SPAN_COUNT = 2

@AndroidEntryPoint
class SearchByTypeFragment : com.app.pokeapi.core.baseUI.BaseFragment<FragmentSearchByTypeBinding>() {

    private val viewModel: SearchByTypeViewModel by viewModels()

    private val typesAdapter = TypesMenuAdapter { typeDisplay ->
        onTypeMenuClicked(typeDisplay)
    }

    override fun initUIState() {
        super.initUIState()
        collectState(viewModel.uiState) { state ->
            when (state) {
                is SearchByTypeUIState.BindTypeList -> bindTypesMenu(state.list)
                is SearchByTypeUIState.ShowLoader -> showLoader(state.visible)
                is SearchByTypeUIState.ShowGenericError -> showGenericError(
                    state.error,
                    state.onRetry
                )
            }
        }
    }

    override fun initViews() {
        super.initViews()
        binding.rvTypeList.layoutManager = GridLayoutManager(context, GRID_SPAN_COUNT)
        binding.rvTypeList.adapter = typesAdapter
    }

    private fun onTypeMenuClicked(typeDisplay: TypeDisplay) {
        findNavController().navigate(
            SearchByTypeFragmentDirections.actionSearchByTypeFragmentToTypeDetailFragment(
                typeDisplay.name
            )
        )
    }

    private fun bindTypesMenu(typesMenu: List<TypeDisplay>) {
        typesAdapter.bindData(typesMenu)
    }

    override fun inflateViewBinding(layoutInflater: LayoutInflater) =
        FragmentSearchByTypeBinding.inflate(layoutInflater)
}