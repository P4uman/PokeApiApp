package com.app.pokeapi.core.di

import android.view.LayoutInflater
import com.app.pokeapi.ui.activity.activityMainFlow.MainFlowView
import com.app.pokeapi.ui.fragment.fragmentSearchByType.SearchByTypeView
import com.app.pokeapi.ui.fragment.fragmentTypeDetail.TypeDetailView
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject
import javax.inject.Provider

@Module
@InstallIn(FragmentComponent::class)
class FragmentViewModule
@Inject constructor() {
    fun injectSearchByTypeView(layoutInflater: LayoutInflater) = SearchByTypeView(layoutInflater)
    fun injectTypeDetailView(layoutInflater: LayoutInflater) = TypeDetailView(layoutInflater)
}