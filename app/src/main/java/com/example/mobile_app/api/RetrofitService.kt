package com.example.mobile_app.visaobarbeiro.api

import com.example.mobile_app.RetrofitServiceApagada
import com.example.mobile_app.login.ApiLogin
import com.example.mobile_app.visaobarbeiro.telas_agendamento.ApiAgendamentos
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.ApiBarbeiros
import com.example.mobile_app.visaobarbeiro.telas_servico.ApiServicos
import com.example.mobile_app.visaocliente.componentes.meuperfilcomponentes.ApiDadosCliente
import com.example.mobile_app.visaocliente.componentes.muralcomponentes.ApiMural
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private const val BASE_URL_APIREST = "http://3.233.14.6:8080/"

    // Inicialmente, o token é nulo
    private val authInterceptor = AuthInterceptor(null.toString())

    private val client = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_APIREST)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val apiBarbeiros: ApiBarbeiros = retrofit.create(ApiBarbeiros::class.java)
    val apiServicos: ApiServicos = retrofit.create(ApiServicos::class.java)
    val apiAgendamentos: ApiAgendamentos = retrofit.create(ApiAgendamentos::class.java)
    val getApiAviso: ApiMural = retrofit.create(ApiMural::class.java)

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
