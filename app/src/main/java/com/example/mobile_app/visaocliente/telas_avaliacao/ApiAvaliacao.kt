package com.example.mobile_app.visaocliente.telas_avaliacao

import com.example.mobile_app.visaocliente.telas_avaliacao.componentes.Avaliacao
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiAvaliacao {
    @POST("avaliacoes/{idBarbearia}")
    suspend fun cadastrarAvaliacao(
        @Path("idBarbearia") id: Long,
        @Query("idCliente") idCliente: Long,
        @Body avaliacao: Avaliacao
    ): Response<Void>

}