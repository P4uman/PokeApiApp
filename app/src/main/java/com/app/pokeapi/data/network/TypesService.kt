package com.app.pokeapi.data.network

import com.app.pokeapi.RetrofitHelper
import com.app.pokeapi.data.network.entities.TypeEntityList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TypesService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getTypeList(): TypeEntityList? {
        // Execute call in another thread using coroutines
        return withContext(Dispatchers.IO) {
            retrofit.create(PokeApi::class.java).getTypes().body()
        }
    }
}