package com.example.mobile_app.visaobarbeiro.telas_agendamento

import AgendamentoListagemDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiAgendamentos {

    @GET("agendamentos/barbeiro/{barbeiroId}")
    suspend fun getAgendamentoPorId(@Path("barbeiroId") barbeiroId: Long): Response<List<AgendamentoListagemDto>>

}