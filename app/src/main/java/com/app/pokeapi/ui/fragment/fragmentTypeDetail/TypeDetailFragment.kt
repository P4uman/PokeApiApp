package com.app.pokeapi.ui.fragment.fragmentTypeDetail

import android.view.LayoutInflater
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.app.pokeapi.core.baseUI.BaseFragmentMVVM
import com.app.pokeapi.ui.activity.activityMainFlow.LoaderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TypeDetailFragment : BaseFragmentMVVM<TypeDetailView>() {

    private val viewModel: TypeDetailViewModel by viewModels()
    private val loaderViewModel: LoaderViewModel by activityViewModels()
    private val args: TypeDetailFragmentArgs by navArgs()

    override fun injectView(layoutInflater: LayoutInflater) =
        module.injectTypeDetailView(layoutInflater)

    override fun init() {
        viewModel.init(args.typeID)
    }

    override fun initObservers() {
        viewModel.typeDetail.observe(viewLifecycleOwner) { typeDetailDisplay ->
            mView.bindTypeDetail(typeDetailDisplay)
        }
        viewModel.showLoading.observe(viewLifecycleOwner) { showLoading ->
            loaderViewModel.showLoading(showLoading)
        }
    }
}