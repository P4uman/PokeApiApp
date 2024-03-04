package com.app.pokeapi.features.searchByType.fragment.fragmentTypeDetail.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.pokeapi.databinding.ItemPokemonBinding
import com.app.pokeapi.model.PokemonShortModel

class PokemonListAdapter : RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {

    private var pokemonList: List<PokemonShortModel> = ArrayList(0)

    inner class ViewHolder(binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var name = binding.tvName
    }

    @SuppressLint("NotifyDataSetChanged")
    fun bindData(pokemonList: List<PokemonShortModel>) {
        this.pokemonList = ArrayList(pokemonList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemPokemonBinding.inflate(LayoutInflater.from(parent.context))
    )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = pokemonList[position].pokemonName
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

}