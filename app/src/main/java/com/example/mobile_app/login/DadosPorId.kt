package com.example.mobile_app.login

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DadosPorId {
    @GET("clientes/usuario")
    suspend fun getDadosCliente(@Query("idUsuario") id: Long): Response<DadosUsuario>

    @GET("barbeiros/usuario")
    suspend fun getDadosBarbeiros(@Query("idUsuario") id: Long): Response<DadosUsuario>
}