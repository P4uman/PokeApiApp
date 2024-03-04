package com.app.pokeapi.features.searchByType.fragment.fragmentSearchByType.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.pokeapi.databinding.ItemTypeBinding
import com.app.pokeapi.model.type.TypeDisplay

class TypesMenuAdapter(
private val onItemClickListener: (TypeDisplay) -> Unit
) : RecyclerView.Adapter<TypesMenuAdapter.ViewHolder>() {

    private var typeList: List<TypeDisplay> = ArrayList(0)

    inner class ViewHolder(binding: ItemTypeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val title = binding.tvTitle
        val linearContainer = binding.llContainer
        val icon = binding.ivIcon
    }

    @SuppressLint("NotifyDataSetChanged")
    fun bindData(typeList: List<TypeDisplay>) {
        this.typeList = ArrayList(typeList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemTypeBinding.inflate(LayoutInflater.from(parent.context))
    )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val type = typeList[position]
        holder.title.text = type.name
        holder.icon.setImageResource(type.resources.icon)
        holder.linearContainer.setBackgroundResource(type.resources.color)
        holder.linearContainer.setOnClickListener {
            onItemClickListener.invoke(type)
        }
    }

    override fun getItemCount(): Int {
        return typeList.size
    }

}