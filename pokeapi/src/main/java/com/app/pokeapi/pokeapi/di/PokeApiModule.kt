package com.app.pokeapi.pokeapi.di

import com.app.pokeapi.pokeapi.domain.repository.PokeApiRepository
import com.app.pokeapi.pokeapi.domain.repository.PokeApiRepositoryImp
import com.app.pokeapi.pokeapi.domain.useCase.getTypeDetail.GetTypeDetailUseCase
import com.app.pokeapi.pokeapi.domain.useCase.getTypeDetail.GetTypeDetailUseCaseImp
import com.app.pokeapi.pokeapi.domain.useCase.getTypeList.GetTypeListUseCase
import com.app.pokeapi.pokeapi.domain.useCase.getTypeList.GetTypeListUseCaseImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    @Singleton
    abstract fun providePokeApiRepository(imp: PokeApiRepositoryImp): PokeApiRepository

    @Binds
    @Singleton
    abstract fun provideGetTypeListUseCase(imp: GetTypeListUseCaseImp): GetTypeListUseCase

    @Binds
    @Singleton
    abstract fun provideGetTypeDetailUseCase(imp: GetTypeDetailUseCaseImp): GetTypeDetailUseCase
}