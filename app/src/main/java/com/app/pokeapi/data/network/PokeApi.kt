package com.app.pokeapi.data.network

import com.app.pokeapi.data.network.entities.TypeEntityList
import retrofit2.Response
import retrofit2.http.GET

interface PokeApi {

    @GET("/api/v2/type")
    suspend fun getTypes() : Response<TypeEntityList>
}