package com.app.pokeapi

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.pokeapi.databinding.ActivityMainBinding
import com.app.pokeapi.databinding.ItemTypeBinding
import com.app.pokeapi.domain.model.TypeModel
import com.app.pokeapi.domain.useCase.GetTypeListUseCase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val typesAdapter : TypesAdapter by lazy {
        TypesAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.init()
        initViews()
        initObservers()
    }

    private fun initViews() {
        binding.rvTypeList.layoutManager = LinearLayoutManager(this)
        binding.rvTypeList.adapter = typesAdapter
    }

    private fun initObservers() {
        viewModel.typeListModel.observe(this) { typeList ->
            typesAdapter.bindData(typeList)
        }
    }

    class TypesAdapter(
    ) : RecyclerView.Adapter<TypesAdapter.ViewHolder>() {

        private var questionsList: List<TypeModel> = ArrayList(0)

        inner class ViewHolder(binding: ItemTypeBinding) :
            RecyclerView.ViewHolder(binding.root) {
            val title = binding.tvTitle
        }

        @SuppressLint("NotifyDataSetChanged")
        fun bindData(typeList: List<TypeModel>) {
            questionsList = ArrayList(typeList)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
            ItemTypeBinding.inflate(LayoutInflater.from(parent.context))
        )


        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.title.text = questionsList[position].typeName
        }

        override fun getItemCount(): Int {
            return questionsList.size
        }

    }
}