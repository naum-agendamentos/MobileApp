package com.example.mobile_app.visaobarbeiro.api

import com.example.mobile_app.visaobarbeiro.telas_barbeiro.ApiBarbeiros
import com.example.mobile_app.visaobarbeiro.telas_servico.ApiServicos
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private const val BASE_URL_BARBEIRO = "http://192.168.15.4:8080/"

    private val token = "yJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJndWlsaGVybWVAZ21haWwuY29tIiwiaWF0IjoxNzI5NTM2NTk5LCJleHAiOjE3MzMxMzY1OTl9.F9TAg0i5RxLBTL_H2yNGbMQi5ht2doxqu6NpNk2YDTJ5zD_zJeIvAWEjhTwDCwVEFR8vn3OPLb46r_pqy8Hiyg"

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
}
