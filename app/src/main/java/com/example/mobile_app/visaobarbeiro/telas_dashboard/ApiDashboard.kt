package com.example.mobile_app.visaobarbeiro.telas_dashboard

import com.example.mobile_app.visaobarbeiro.telas_barbeiro.ver_barbeiro.componente.VerBarbeiro
import com.example.mobile_app.visaobarbeiro.telas_dashboard.componentes.BarbeiroLucroListagemDto
import com.example.mobile_app.visaobarbeiro.telas_dashboard.componentes.BarbeiroQtdCortesListagemDto
import com.example.mobile_app.visaobarbeiro.telas_dashboard.componentes.ServicoQtdMesListagemDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiDashboard {

    @GET("dashboards/cortes-por-barbeiro")
    suspend fun getCortePorBarbeiro(): Response<List<BarbeiroQtdCortesListagemDto>>


    @GET("dashboards/lucro-por-barbeiro")
    suspend fun getLucroPorBarbeiro(): Response<List<BarbeiroLucroListagemDto>>


    @GET("dashboards/top-servicos")
    suspend fun getTopServicos(): Response<List<ServicoQtdMesListagemDto>>


    @GET("dashboards/lucro")
    suspend fun getLucro(): Response<Double>


    @GET("dashboards/total-agendamento-hoje")
    suspend fun geTotalAgendDia(): Response<Int>


    @GET("dashboards/porcentagem-agendamento-hoje-ontem")
    suspend fun getPorcAgendamentoOntemHoje(): Response<Double>


    @GET("dashboards/media-avaliacao/{idBarbearia}")
    suspend fun getMediaAvaliacao(@Path("idBarbearia") idBarbearia: Long): Response<Double>
}