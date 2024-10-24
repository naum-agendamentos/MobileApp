package com.example.mobile_app.visaobarbeiro.api

import com.example.mobile_app.visaobarbeiro.telas_agendamento.ApiAgendamentos
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.ApiBarbeiros
import com.example.mobile_app.visaobarbeiro.telas_servico.ApiServicos
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private const val BASE_URL_BARBEIRO = "http://3.233.14.6:8080/"

    private val token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJndWlsaGVybWVAZ21haWwuY29tIiwiaWF0IjoxNzI5NTU1MzcwLCJleHAiOjE3MzMxNTUzNzB9.G4P7ybqcSMSOrjkMK4mg4P24OKGhA3BWJsSqsflyLIsyxBjhb9erHconfmnrEhqj_P54pzlrhjpp86Tk204_VQ"

    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(token))
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_BARBEIRO)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiBarbeiros: ApiBarbeiros = retrofit.create(ApiBarbeiros::class.java)
    val apiServicos: ApiServicos = retrofit.create(ApiServicos::class.java)
    val apiAgendamentos: ApiAgendamentos = retrofit.create(ApiAgendamentos::class.java)

}
