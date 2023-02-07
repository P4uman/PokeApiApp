package com.app.pokeapi.ui.fragment.fragmentSearchByType

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import com.app.pokeapi.core.baseUI.BaseViewMVVM
import com.app.pokeapi.databinding.FragmentSearchByTypeBinding
import com.app.pokeapi.ui.display.TypeDisplay

private const val GRID_SPAN_COUNT = 2

class SearchByTypeView(
    layoutInflater: LayoutInflater
) : BaseViewMVVM<FragmentSearchByTypeBinding, SearchByTypeView.Listeners>(layoutInflater) {

    interface Listeners: BaseViewListeners {
        fun onTypeMenuClicked(typeDisplay: TypeDisplay)
    }

    private val typesAdapter: TypesMenuAdapter
    private val context: Context get() = binding.root.context

    init {
        binding.rvTypeList.layoutManager = GridLayoutManager(context, GRID_SPAN_COUNT)
        typesAdapter = TypesMenuAdapter { typeDisplay ->
            for (listener in listeners) {
                listener.onTypeMenuClicked(typeDisplay)
            }
        }
        binding.rvTypeList.adapter = typesAdapter
    }

    override fun inflateViewBinding(layoutInflater: LayoutInflater) =
        FragmentSearchByTypeBinding.inflate(layoutInflater)

    fun bindTypesMenu(typesMenu: List<TypeDisplay>) {
        typesAdapter.bindData(typesMenu)
    }
}