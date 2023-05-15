package com.app.pokeapi.pokeapi.di

import com.app.pokeapi.pokeapi.data.datasource.PokeApiDataSource
import com.app.pokeapi.pokeapi.data.datasource.PokeApiDataSourceImp
import com.app.pokeapi.pokeapi.data.service.PokeApi
import com.app.pokeapi.pokeapi.data.service.PokeApiService
import com.app.pokeapi.pokeapi.domain.repository.PokeApiRepository
import com.app.pokeapi.pokeapi.domain.repository.PokeApiRepositoryImp
import com.app.pokeapi.pokeapi.domain.useCase.getTypeDetail.GetTypeDetailUseCase
import com.app.pokeapi.pokeapi.domain.useCase.getTypeDetail.GetTypeDetailUseCaseImp
import com.app.pokeapi.pokeapi.domain.useCase.getTypeList.GetTypeListUseCase
import com.app.pokeapi.pokeapi.domain.useCase.getTypeList.GetTypeListUseCaseImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.app.pokeapi.pokeapi.BuildConfig

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_POKEAPI)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun providePokeApi(retrofit: Retrofit): PokeApi =
        retrofit.create(PokeApi::class.java)

    @Provides
    @Singleton
    fun providePokeApiDataSource(
        service: PokeApiService
    ): PokeApiDataSource = PokeApiDataSourceImp(service)
}

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