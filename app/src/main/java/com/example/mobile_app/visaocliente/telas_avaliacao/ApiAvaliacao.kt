package com.example.mobile_app.visaocliente.telas_avaliacao

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiAvaliacao {
    @POST("avaliacoes/{idBarbearia}")
    suspend fun cadastrarAvaliacao(@Path("idBarbearia") id: Long, @Body qtdEstrelas: Int)
}