package com.app.pokeapi.ui.fragment.fragmentTypeDetail

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.pokeapi.core.baseUI.BaseFragment
import com.app.pokeapi.core.extensions.collectState
import com.app.pokeapi.databinding.FragmentTypeDetailBinding
import com.app.pokeapi.ui.fragment.fragmentTypeDetail.adapter.PokemonListAdapter
import com.app.pokeapi.ui.fragment.fragmentTypeDetail.model.TypeDetailDisplay
import com.app.pokeapi.ui.fragment.fragmentTypeDetail.model.TypeDetailUIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TypeDetailFragment : BaseFragment<FragmentTypeDetailBinding>() {

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

    private fun bindTypeDetail(display: TypeDetailDisplay) {
        pokemonAdapter.bindData(display.pokemonByType)
    }

    override fun inflateViewBinding(layoutInflater: LayoutInflater) =
        FragmentTypeDetailBinding.inflate(layoutInflater)
}