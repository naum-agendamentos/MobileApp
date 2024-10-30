package com.example.mobile_app

import com.example.mobile_app.login.ApiLogin
import com.example.mobile_app.visaobarbeiro.api.AuthInterceptorApagada
import com.example.mobile_app.visaobarbeiro.api.RetrofitServiceApagada2
import com.example.mobile_app.visaocliente.componentes.muralcomponentes.ApiMural
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitServiceApagada {

    private const val BASE_URL_BARBEIRO = "http://3.233.14.6:8080/"

    private val token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJndWlsaGVybWVAZ21haWwuY29tIiwiaWF0IjoxNzMwMTUxMDgwLCJleHAiOjE3MzM3NTEwODB9.wT-aKvrFLWhJlCxp0fYyv9V3BnIjffbSXw-skjsPzuobmCy9Ksxk1HECitABwRy1Zq_gKnXPKiz_hmcUWpKQvA"

    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptorApagada(token))
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_BARBEIRO)
        //.client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val getApiAviso: ApiMural = RetrofitServiceApagada2.retrofit.create(ApiMural::class.java)

    val apiLogin : ApiLogin = RetrofitServiceApagada.retrofit.create(ApiLogin::class.java)




}