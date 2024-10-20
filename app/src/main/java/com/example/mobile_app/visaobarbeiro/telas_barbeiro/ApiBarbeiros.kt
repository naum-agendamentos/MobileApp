package com.example.mobile_app.visaobarbeiro.telas_barbeiro

import com.example.mobile_app.visaobarbeiro.telas_barbeiro.cadastrar_barbeiro.componente.CadastrarBarbeiro
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.ver_barbeiro.componente.VerBarbeiro
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiBarbeiros {
    @GET("barbeiros")
    suspend fun get(): Response<List<VerBarbeiro>>

    @POST("barbeiros")
    suspend fun cadastrar(@Body novoBarbeiro: CadastrarBarbeiro): Response<VerBarbeiro>
}
