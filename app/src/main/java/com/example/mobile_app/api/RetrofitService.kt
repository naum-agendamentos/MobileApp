package com.example.mobile_app.visaobarbeiro.api

import com.example.mobile_app.login.ApiLogin
import com.example.mobile_app.login.DadosPorId
import com.example.mobile_app.visaobarbeiro.telas_agendamento.ApiAgendamentos
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.ApiBarbeiros
import com.example.mobile_app.visaobarbeiro.telas_dashboard.ApiDashboard
import com.example.mobile_app.visaobarbeiro.telas_servico.ApiServicos
import com.example.mobile_app.visaocliente.telas_agendamento.agendamento_datahora.ApiAgendamentoCliente
import com.example.mobile_app.visaocliente.telas_editarPerfil.ApiDadosCliente
import com.example.mobile_app.visaocliente.telas_muralAvisos.ApiMural
import com.example.mobile_app.visaocliente.telas_agendamento.servicos_agendamento.ApiServico
import com.example.mobile_app.visaocliente.telas_avaliacao.ApiAvaliacao
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private const val BASE_URL_APIREST = "http://3.233.162.17/"

    private val initialToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJndWlsaGVybWVAZ21haWwuY29tIiwiaWF0IjoxNzMyNTEzNTIyLCJleHAiOjE3MzYxMTM1MjJ9.SgwxMQ3M2ZNcVebvm9KojrzJFfiSxn7lyCBwqIsTzloQQcK6PrIxoF_8vN9NO1ZaGIxrkzmbRHQ5EeMDdmPSUw"

    private val authInterceptor = AuthInterceptor(initialToken)

    private val client = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_APIREST)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiBarbeiros: ApiBarbeiros = retrofit.create(ApiBarbeiros::class.java)
    val apiServicos: ApiServicos = retrofit.create(ApiServicos::class.java)
    val apiServico: ApiServico = retrofit.create(ApiServico::class.java)
    val apiAgendamentos: ApiAgendamentos = retrofit.create(ApiAgendamentos::class.java)
    val apiAgendamentosCliente: ApiAgendamentos = retrofit.create(ApiAgendamentos::class.java)
    val apiAgendamentoCliente: ApiAgendamentoCliente = retrofit.create(ApiAgendamentoCliente::class.java)
    val getApiAviso: ApiMural = retrofit.create(ApiMural::class.java)
    val apiAvaliacao: ApiAvaliacao = retrofit.create(ApiAvaliacao::class.java)
    val apiDadosPorId: DadosPorId = retrofit.create(DadosPorId::class.java)
    val apiDadosCliente: ApiDadosCliente = retrofit.create(ApiDadosCliente::class.java)
    val apiDashboard: ApiDashboard = retrofit.create(ApiDashboard::class.java)

    // Método para atualizar o token no interceptor
    fun updateToken(newToken: String) {
        authInterceptor.setToken(newToken)
    }

    val retrofitLogin: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_APIREST)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiLogin: ApiLogin = retrofitLogin.create(ApiLogin::class.java)
}
