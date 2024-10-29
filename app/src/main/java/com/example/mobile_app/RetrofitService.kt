package com.example.mobile_app

import com.example.mobile_app.visaobarbeiro.api.AuthInterceptor
import com.example.mobile_app.visaobarbeiro.api.RetrofitService
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.ApiBarbeiros
import com.example.mobile_app.visaocliente.componentes.muralcomponentes.ApiMural
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime

object RetrofitService {

    private const val BASE_URL_BARBEIRO = "http://3.233.14.6:8080/"

    private val token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJndWlsaGVybWVAZ21haWwuY29tIiwiaWF0IjoxNzMwMTUxMDgwLCJleHAiOjE3MzM3NTEwODB9.wT-aKvrFLWhJlCxp0fYyv9V3BnIjffbSXw-skjsPzuobmCy9Ksxk1HECitABwRy1Zq_gKnXPKiz_hmcUWpKQvA"

    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(token))
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_BARBEIRO)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val getApiAviso: ApiMural = RetrofitService.retrofit.create(ApiMural::class.java)


}