package com.app.pokeapi.pokeapi.data.service

import com.app.pokeapi.pokeapi.data.entities.TypeDetailEntity
import com.app.pokeapi.pokeapi.data.entities.TypeEntityList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {

    @GET("/api/v2/type")
    suspend fun getTypes() : Response<TypeEntityList>

    @GET("/api/v2/type/{type_id}")
    suspend fun getTypeDetail(
        @Path(value = "type_id") typeID: String
    ) : Response<TypeDetailEntity>
}