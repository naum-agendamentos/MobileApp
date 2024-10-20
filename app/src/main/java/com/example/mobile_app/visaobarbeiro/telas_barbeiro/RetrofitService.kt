package com.example.mobile_app.visaobarbeiro.telas_barbeiro

import AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private const val BASE_URL_BARBEIRO = "http://192.168.15.12:8080/"

    private val token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJndWlsaGVybWVAZ21haWwuY29tIiwiaWF0IjoxNzI5Mzg2OTQyLCJleHAiOjE3MzI5ODY5NDJ9.kswUnkyPy_XKF0oSeLuctJzxtSGe3VB6d_gdYYrtHZWEj70b7n_INTqsSq_7dag4s6dBw2b8pJsNliDXfZcOHg"

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
