package com.app.pokeapi.ui.fragment.fragmentSearchByType

import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.app.pokeapi.core.baseUI.BaseFragmentMVVM
import com.app.pokeapi.databinding.FragmentSearchByTypeBinding
import com.app.pokeapi.ui.activity.activityMainFlow.LoaderViewModel
import com.app.pokeapi.ui.display.TypeDisplay
import dagger.hilt.android.AndroidEntryPoint

private const val GRID_SPAN_COUNT = 2
@AndroidEntryPoint
class SearchByTypeFragment : BaseFragmentMVVM<FragmentSearchByTypeBinding>() {

    private val viewModel: SearchByTypeViewModel by viewModels()
    private val loaderViewModel: LoaderViewModel by activityViewModels()

    private val typesAdapter = TypesMenuAdapter { typeDisplay ->
        onTypeMenuClicked(typeDisplay)
    }

    override fun initViews() {
        super.initViews()
        binding.rvTypeList.layoutManager = GridLayoutManager(context, GRID_SPAN_COUNT)
        binding.rvTypeList.adapter = typesAdapter
    }

    override fun initObservers() {
        viewModel.showLoader.observe(viewLifecycleOwner) { loaderVisible ->
            loaderViewModel.showLoading(loaderVisible)
        }
        viewModel.typeListDisplay.observe(viewLifecycleOwner) { typeList ->
            bindTypesMenu(typeList)
        }
    }

    private fun onTypeMenuClicked(typeDisplay: TypeDisplay) {
        Toast.makeText(context, typeDisplay.name, Toast.LENGTH_LONG).show()
        findNavController().navigate(SearchByTypeFragmentDirections.actionSearchByTypeFragmentToTypeDetailFragment(typeDisplay.name))
    }

    private fun  bindTypesMenu(typesMenu: List<TypeDisplay>) {
        typesAdapter.bindData(typesMenu)
    }

    override fun inflateViewBinding(layoutInflater: LayoutInflater)
    = FragmentSearchByTypeBinding.inflate(layoutInflater)
}