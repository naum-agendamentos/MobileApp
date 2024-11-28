package com.example.mobile_app.visaocliente.telas_agendamento.agendamento_datahora

import AgendamentoListagemDto
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.ver_barbeiro.componente.VerBarbeiro
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiAgendamentoCliente {
    @POST("/agendamentos")
    suspend fun criarAgendamento(
        @Query("barbeiroId") barbeiroId: Long,
        @Query("clienteId") clienteId: Long,
        @Query("servicoIds") servicoIds: List<Long>,
        @Query("inicio") inicio: String
    ): Response<Agendamento>

    @GET("/agendamentos/{id}")
    suspend fun buscarAgendamento(
        @Path("id") id: Long
    ): Response<Agendamento>

    @GET("agendamentos/cliente/{clienteId}")
    suspend fun getAgendamentosPorCliente(@Path("clienteId") clienteId: Long): Response<List<AgendamentoListagemDto>>



    @GET("barbeiros")
    suspend fun get(): Response<List<VerBarbeiro>>

    @GET("/agendamentos/barbeiro/{barbeiroId}")
    suspend fun listarAgendamentosPorBarbeiro(
        @Path("barbeiroId") barbeiroId: Long
    ): Response<List<Agendamento>>

    @GET("/agendamentos/barbeiroBloq/{barbeiroId}")
    suspend fun listarAgendamentosPorBarbeiroBloq(
        @Path("barbeiroId") barbeiroId: Long
    ): Response<List<Agendamento>>

    @PUT("/agendamentos/{idAgendamento}")
    suspend fun atualizarAgendamento(
        @Path("idAgendamento") idAgendamento: Long,
        @Query("barbeiroId") barbeiroId: Long,
        @Query("clienteId") clienteId: Long,
        @Query("servicoIds") servicoIds: List<Long>,
        @Query("inicio") inicio: String
    ): Response<Agendamento>

    @DELETE("/agendamentos/{idAgendamento}")
    suspend fun excluirAgendamento(
        @Path("idAgendamento") idAgendamento: Long
    ): Response<Unit>

    @GET("/agendamentos/cliente/{clienteId}")
    suspend fun listarAgendamentosPorCliente(
        @Path("clienteId") clienteId: Long
    ): Response<List<Agendamento>>

    @POST("/agendamentos/bloquearHorarios")
    suspend fun bloquearHorarios(
        @Query("barbeiroId") barbeiroId: Long,
        @Query("datas") datas: List<String>
    ): Response<List<Agendamento>>
}