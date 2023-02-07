package com.app.pokeapi.ui.fragment.fragmentTypeDetail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.pokeapi.core.baseUI.BaseViewMVVM
import com.app.pokeapi.databinding.FragmentTypeDetailBinding
import com.app.pokeapi.databinding.ItemPokemonBinding
import com.app.pokeapi.ui.display.PokemonShortDisplay
import com.app.pokeapi.ui.display.TypeDetailDisplay

class TypeDetailView(
    layoutInflater: LayoutInflater
) : BaseViewMVVM<FragmentTypeDetailBinding, BaseViewMVVM.BaseViewListeners>(layoutInflater) {

    private val pokemonAdapter: PokemonListAdapter

    init {
        binding.rvTypePokeList.layoutManager = LinearLayoutManager(context)
        pokemonAdapter = PokemonListAdapter()
        binding.rvTypePokeList.adapter = pokemonAdapter
    }

    override fun inflateViewBinding(layoutInflater: LayoutInflater) =
        FragmentTypeDetailBinding.inflate(layoutInflater)

    fun bindTypeDetail(display: TypeDetailDisplay) {
        pokemonAdapter.bindData(display.pokemonByType)
    }
}

class PokemonListAdapter : RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {

    private var pokemonList: List<PokemonShortDisplay> = ArrayList(0)

    inner class ViewHolder(binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var name = binding.tvName
    }

    @SuppressLint("NotifyDataSetChanged")
    fun bindData(pokemonList: List<PokemonShortDisplay>) {
        this.pokemonList = ArrayList(pokemonList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemPokemonBinding.inflate(LayoutInflater.from(parent.context))
    )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = pokemonList[position].name
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

}