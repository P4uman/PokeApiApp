package com.app.pokeapi.di

import com.app.pokeapi.repository.PokeApiRepository
import com.app.pokeapi.repository.PokeApiRepositoryImp
import com.app.pokeapi.useCase.getTypeList.GetTypeListUseCase
import com.app.pokeapi.useCase.getTypeList.GetTypeListUseCaseImp
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
}