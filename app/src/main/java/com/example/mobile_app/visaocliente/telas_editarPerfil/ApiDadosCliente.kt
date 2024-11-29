package com.example.mobile_app.visaocliente.telas_editarPerfil

import com.example.mobile_app.visaocliente.telas_agendamento.agendamento_datahora.Cliente
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiDadosCliente {
    @GET("clientes/usuario")
    suspend fun getDadosCliente(@Query("idUsuario") id: Long): Response<DadosCliente>

    @PUT("clientes/{id}")
    suspend fun updateDadosCliente(@Path("id") id: Long, @Body dadosCliente: DadosCliente): Response<Void>

    @DELETE("clientes/{id}")
    suspend fun deleteDadosCliente(@Path("id") id: Long): Response<Void>

    @POST("/clientes")
    suspend fun cadastrarCliente(@Body cliente: Cliente): Response<Void>
}