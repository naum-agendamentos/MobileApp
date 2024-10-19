package com.example.mobile_app.visaobarbeiro.telas_barbeiro

import com.example.mobile_app.visaobarbeiro.telas_barbeiro.ver_barbeiro.componente.ApiBarbeiros
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private const val BASE_URL_BARBEIRO = "https://66d5c026f5859a70426752fb.mockapi.io/mural/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_BARBEIRO)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiBarbeiros: ApiBarbeiros = retrofit.create(ApiBarbeiros::class.java)
}
