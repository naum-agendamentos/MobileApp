package com.example.mobile_app.visaocliente.telas_agendamento.servicos_agendamento

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServico {
    @POST("/servicos")
    suspend fun criarServico(
        @Body novoServico: Servico,
        @Query("idBarbearia") idBarbearia: Long
    ): Response<Servico>

    @GET("/servicos")
    suspend fun listarServicos(
        @Query("idBarbearia") idBarbearia: Long
    ): Response<List<Servico>>

    @GET("/servicos/{idServico}")
    suspend fun listarServicoPorId(
        @Path("idServico") idServico: Long,
        @Query("idBarbearia") idBarbearia: Long
    ): Response<Servico>

    @PUT("/servicos/{idServico}")
    suspend fun atualizarServico(
        @Path("idServico") idServico: Long,
        @Body servicoAtualizado: Servico
    ): Response<Servico>

    @DELETE("/servicos/{idServico}")
    suspend fun excluirServico(
        @Path("idServico") idServico: Long
    ): Response<Unit>

    @GET("servicos")
    suspend fun get(
        @Query("idBarbearia") idBarbearia: Long): Response<List<Servico>>
}
