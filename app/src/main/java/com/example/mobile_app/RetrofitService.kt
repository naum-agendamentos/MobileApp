package com.example.homepage

import com.example.homepage.visaocliente.componentes.muralcomponentes.ApiMural
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    val BASE_URL_FEIRA = "http://10.0.2.2:8080/"

    // função que retorna o cliente para a API de filmes
    fun getApiAviso(): ApiMural {
        val cliente =
            Retrofit.Builder()
                .baseUrl(BASE_URL_FEIRA)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiMural::class.java)

        return cliente
    }
}