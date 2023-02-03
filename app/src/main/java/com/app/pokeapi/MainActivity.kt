package com.app.pokeapi

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.pokeapi.databinding.ActivityMainBinding
import com.app.pokeapi.databinding.ItemTypeBinding
import com.app.pokeapi.ui.TypeDisplay

private const val GRID_SPAN_COUNT = 2

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var typesAdapter: TypesAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.init()
        initViews()
        initObservers()
    }

    private fun initViews() {
        binding.rvTypeList.layoutManager = GridLayoutManager(this, GRID_SPAN_COUNT)
        typesAdapter = TypesAdapter { typeDisplay ->
            Toast.makeText(this, typeDisplay.name, Toast.LENGTH_LONG).show()
        }
        binding.rvTypeList.adapter = typesAdapter
    }

    private fun initObservers() {
        viewModel.typeListModel.observe(this) { typeList ->
            typesAdapter.bindData(typeList)
        }
    }

    class TypesAdapter(
        private val onItemClickListener: (TypeDisplay) -> Unit
    ) : RecyclerView.Adapter<TypesAdapter.ViewHolder>() {

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
            holder.icon.setImageResource(type.icon)
            holder.linearContainer.setBackgroundResource(type.color)
            holder.linearContainer.setOnClickListener {
                onItemClickListener.invoke(type)
            }
        }

        override fun getItemCount(): Int {
            return typeList.size
        }

    }
}