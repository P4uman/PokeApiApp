package com.app.pokeapi.ui.fragment.fragmentTypeDetail

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.pokeapi.core.baseUI.BaseFragment
import com.app.pokeapi.databinding.FragmentTypeDetailBinding
import com.app.pokeapi.ui.fragment.fragmentTypeDetail.adapter.PokemonListAdapter
import com.app.pokeapi.ui.fragment.fragmentTypeDetail.model.TypeDetailDisplay
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TypeDetailFragment : BaseFragment<FragmentTypeDetailBinding>() {

    private val viewModel: TypeDetailViewModel by viewModels()
    private val args: TypeDetailFragmentArgs by navArgs()

    private val pokemonAdapter = PokemonListAdapter()

    override fun initViews() {
        super.initViews()
        binding.rvTypePokeList.layoutManager = LinearLayoutManager(context)
        binding.rvTypePokeList.adapter = pokemonAdapter

        viewModel.init(args.typeID)
    }

    override fun initObservers() {
        viewModel.typeDetail.observe(viewLifecycleOwner) { typeDetailDisplay ->
            bindTypeDetail(typeDetailDisplay)
        }
        viewModel.showLoading.observe(viewLifecycleOwner) { showLoading ->
            showLoader(showLoading)
        }
    }

    private fun bindTypeDetail(display: TypeDetailDisplay) {
        pokemonAdapter.bindData(display.pokemonByType)
    }

    override fun inflateViewBinding(layoutInflater: LayoutInflater) =
        FragmentTypeDetailBinding.inflate(layoutInflater)
}