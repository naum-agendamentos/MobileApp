package com.example.mobile_app.visaocliente.componentes.muralcomponentes

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiMural {
    @GET("/mural-avisos")
    suspend fun get(): Response<List<Aviso>>;

    @DELETE("/mural-avisos/{id}")
    suspend fun delete(@Path("id") id: Int): Response<Unit>;

    @POST("/mural-avisos") // mapeia o endpoint POST /filmes
    suspend fun post(@Body aviso: Aviso): Response<Aviso>

    @PUT("/mural-avisos/{id}") // mapeia o endpoint PUT /filmes/{id}
    suspend fun put(@Path("id") id:Int, @Body Aviso: Aviso): Response<Aviso>

}