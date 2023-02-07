package com.app.pokeapi.core.di

import android.view.LayoutInflater
import com.app.pokeapi.ui.activity.activityMainFlow.MainFlowView
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject

@Module
@InstallIn(ActivityComponent::class)
class ActivityViewModule
@Inject constructor() {
    fun injectMainFlowView(layoutInflater: LayoutInflater) = MainFlowView(layoutInflater)
}