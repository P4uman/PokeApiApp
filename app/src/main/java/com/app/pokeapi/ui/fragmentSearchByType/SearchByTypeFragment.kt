package com.app.pokeapi.ui.fragmentSearchByType

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager

import com.app.pokeapi.databinding.FragmentSearchByTypeBinding

private const val GRID_SPAN_COUNT = 2

class SearchByTypeFragment : Fragment() {

    private lateinit var binding: FragmentSearchByTypeBinding
    private lateinit var typesAdapter: TypesMenuAdapter
    private val viewModel: SearchByTypeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchByTypeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init()
        initViews()
        initObservers()
    }

    private fun initViews() {
        binding.rvTypeList.layoutManager = GridLayoutManager(context, GRID_SPAN_COUNT)
        typesAdapter = TypesMenuAdapter { typeDisplay ->
            Toast.makeText(context, typeDisplay.name, Toast.LENGTH_LONG).show()
            findNavController().navigate(SearchByTypeFragmentDirections.actionSearchByTypeFragmentToTypeDetailFragment())
        }
        binding.rvTypeList.adapter = typesAdapter
    }

    private fun initObservers() {
        viewModel.typeListModel.observe(viewLifecycleOwner) { typeList ->
            typesAdapter.bindData(typeList)
        }
    }
}