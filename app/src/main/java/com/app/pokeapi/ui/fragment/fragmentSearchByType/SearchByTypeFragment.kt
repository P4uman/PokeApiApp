package com.app.pokeapi.ui.fragment.fragmentSearchByType

import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.pokeapi.core.baseUI.BaseFragmentMVVM
import com.app.pokeapi.core.di.FragmentViewModule
import com.app.pokeapi.ui.activity.activityMainFlow.LoaderViewModel
import com.app.pokeapi.ui.display.TypeDisplay
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchByTypeFragment : BaseFragmentMVVM<SearchByTypeView>(), SearchByTypeView.Listeners {

    private val viewModel: SearchByTypeViewModel by viewModels()
    private val loaderViewModel: LoaderViewModel by activityViewModels()

    override fun onStart() {
        super.onStart()
        mView.registerListener(this)
    }

    override fun onStop() {
        super.onStop()
        mView.unregisterListener(this)
    }

    override fun initObservers() {
        viewModel.showLoader.observe(viewLifecycleOwner) { loaderVisible ->
            loaderViewModel.showLoading(loaderVisible)
        }
        viewModel.typeListModel.observe(viewLifecycleOwner) { typeList ->
            mView.bindTypesMenu(typeList)
        }
    }

    override fun injectView(layoutInflater: LayoutInflater): SearchByTypeView =
        module.injectSearchByTypeView(layoutInflater)

    override fun onTypeMenuClicked(typeDisplay: TypeDisplay) {
        Toast.makeText(context, typeDisplay.name, Toast.LENGTH_LONG).show()
        findNavController().navigate(SearchByTypeFragmentDirections.actionSearchByTypeFragmentToTypeDetailFragment(typeDisplay.name))
    }
}