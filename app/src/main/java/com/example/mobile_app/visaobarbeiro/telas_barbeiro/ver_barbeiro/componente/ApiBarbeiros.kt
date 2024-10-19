package com.example.mobile_app.visaobarbeiro.telas_barbeiro.ver_barbeiro.componente

import retrofit2.Response
import retrofit2.http.GET

interface ApiBarbeiros {
    @GET("barbeiro")
    suspend fun get(): Response<List<BarbeiroListagemDto>>
}
