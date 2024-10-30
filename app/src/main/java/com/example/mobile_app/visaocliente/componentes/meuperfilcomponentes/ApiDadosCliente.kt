package com.example.mobile_app.visaocliente.componentes.meuperfilcomponentes

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiDadosCliente {
    @GET("clientes/usuario")
    suspend fun getDadosCliente(@Query("idUsuario") id: Long): Response<DadosCliente>

    @PUT("clientes/{id}")
    suspend fun updateDadosCliente(@Path("id") id: Long, @Body dadosCliente: DadosCliente): Response<Void>

}