package com.example.mobile_app

import com.example.mobile_app.visaocliente.componentes.muralcomponentes.ApiMural
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime

object RetrofitService {

    //Coloque o ip do seu computador aqui
    val BASE_URL_FEIRA = "http://SEU IP:8080/"

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