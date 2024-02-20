package com.app.pokeapi.service

import com.app.pokeapi.entities.TypeDetailEntity
import com.app.pokeapi.entities.TypeEntityList
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