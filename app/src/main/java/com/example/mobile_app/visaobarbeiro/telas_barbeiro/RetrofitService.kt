package com.example.mobile_app.visaobarbeiro.telas_barbeiro

import AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private const val BASE_URL_BARBEIRO = "http://192.168.15.12:8080/"

    private val token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJndWlsaGVybWVAZ21haWwuY29tIiwiaWF0IjoxNzI5NDY5Njg5LCJleHAiOjE3MzMwNjk2ODl9.s7W1pXr2yTlxPctM4xfhOc81anH1ZdLlpNzVacpgsy-EVHvzs_j0ICINfm0TrrUGQ1req4iacsbYrUnynjOsLA"

    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(token))
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_BARBEIRO)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiBarbeiros: ApiBarbeiros = retrofit.create(ApiBarbeiros::class.java)
}
