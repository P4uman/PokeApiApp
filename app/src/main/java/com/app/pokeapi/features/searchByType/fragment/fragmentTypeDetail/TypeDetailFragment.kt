package com.app.pokeapi.features.searchByType.fragment.fragmentTypeDetail

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.pokeapi.core.extensions.collectState
import com.app.pokeapi.databinding.FragmentTypeDetailBinding
import com.app.pokeapi.features.searchByType.fragment.fragmentTypeDetail.adapter.PokemonListAdapter
import com.app.pokeapi.features.searchByType.fragment.fragmentTypeDetail.model.TypeDetailUIState
import com.app.pokeapi.model.PokemonShortModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TypeDetailFragment : com.app.pokeapi.core.baseUI.BaseFragment<FragmentTypeDetailBinding>() {

    private val viewModel: TypeDetailViewModel by viewModels()
    private val args: TypeDetailFragmentArgs by navArgs()

    private val pokemonAdapter = PokemonListAdapter()

    override fun initUIState() {
        super.initUIState()
        collectState(viewModel.uiState) {state ->
            when (state) {
                is TypeDetailUIState.BindTypeDetail -> bindTypeDetail(state.display)
                is TypeDetailUIState.ShowLoader -> showLoader(state.visible)
            }
        }
    }
    override fun initViews() {
        super.initViews()
        binding.rvTypePokeList.layoutManager = LinearLayoutManager(context)
        binding.rvTypePokeList.adapter = pokemonAdapter

        viewModel.init(args.typeID)
    }

    private fun bindTypeDetail(display: List<PokemonShortModel>) {
        pokemonAdapter.bindData(display)
    }

    override fun inflateViewBinding(layoutInflater: LayoutInflater) =
        FragmentTypeDetailBinding.inflate(layoutInflater)
}