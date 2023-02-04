package com.app.pokeapi.ui.framgent.fragmentSearchByType

import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.pokeapi.core.baseUI.BaseFragmentMVVM
import com.app.pokeapi.ui.display.TypeDisplay

class SearchByTypeFragment : BaseFragmentMVVM<SearchByTypeView>(), SearchByTypeView.Listeners {

    private val viewModel: SearchByTypeViewModel by viewModels()

    override fun onStart() {
        super.onStart()
        view.registerListener(this)
    }

    override fun onStop() {
        super.onStop()
        view.unregisterListener(this)
    }

    override fun initObservers() {
        viewModel.typeListModel.observe(viewLifecycleOwner) { typeList ->
            view.bindTypesMenu(typeList)
        }
    }

    override fun getView(layoutInflater: LayoutInflater) = SearchByTypeView(layoutInflater)

    override fun onTypeMenuClicked(typeDisplay: TypeDisplay) {
        Toast.makeText(context, typeDisplay.name, Toast.LENGTH_LONG).show()
        findNavController().navigate(SearchByTypeFragmentDirections.actionSearchByTypeFragmentToTypeDetailFragment())
    }
}