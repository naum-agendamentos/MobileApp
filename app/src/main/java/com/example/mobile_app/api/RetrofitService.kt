package com.example.mobile_app.visaobarbeiro.api

import com.example.mobile_app.login.ApiLogin
import com.example.mobile_app.visaobarbeiro.telas_agendamento.ApiAgendamentos
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.ApiBarbeiros
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
    private const val BASE_URL_APIREST = "http://100.26.96.235:8080/"

    private val token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJndWlsaGVybWVAZ21haWwuY29tIiwiaWF0IjoxNzMwMTUxMDgwLCJleHAiOjE3MzM3NTEwODB9.wT-aKvrFLWhJlCxp0fYyv9V3BnIjffbSXw-skjsPzuobmCy9Ksxk1HECitABwRy1Zq_gKnXPKiz_hmcUWpKQvA"

    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(token))
        .build()
    // Inicialmente, o token é nulo
    private val authInterceptor = AuthInterceptor(null.toString())

    private val client2 = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_APIREST)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val apiBarbeiros: ApiBarbeiros = retrofit.create(ApiBarbeiros::class.java)
    val apiServicos: ApiServicos = retrofit.create(ApiServicos::class.java) // barbeiro
    val apiServico: ApiServico = retrofit.create(ApiServico::class.java) // cliente
    val apiAgendamentos: ApiAgendamentos = retrofit.create(ApiAgendamentos::class.java)
    val apiAgendamentoCliente: ApiAgendamentoCliente = retrofit.create(ApiAgendamentoCliente::class.java)
    val getApiAviso: ApiMural = retrofit.create(ApiMural::class.java)
    val apiAvaliacao: ApiAvaliacao = retrofit.create(ApiAvaliacao::class.java)

    val apiDadosCliente : ApiDadosCliente = retrofit.create(ApiDadosCliente::class.java)

    // Método para atualizar o token no interceptor
    fun updateToken(newToken: String) {
        authInterceptor.setToken(newToken)
    }


    val retrofitLogin: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_APIREST)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiLogin : ApiLogin = retrofitLogin.create(ApiLogin::class.java)

}
