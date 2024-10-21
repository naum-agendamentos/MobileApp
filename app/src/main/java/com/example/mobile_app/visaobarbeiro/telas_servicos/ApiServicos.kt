package com.example.mobile_app.visaobarbeiro.telas_servicos

import com.example.mobile_app.visaobarbeiro.telas_servicos.ver_servicos.componente.Servico
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServicos {
    @GET("servicos")
    suspend fun get(
        @Query("idBarbearia") idBarbearia: Long
    ): Response<List<Servico>>


//    @POST("servicos")
//    suspend fun cadastrar(@Body novoBarbeiro: CadastrarBarbeiro): Response<Servico>
//
//    @PUT("servicos/{id}")
//    suspend fun editar(@Path("id") barbeiroId: Long, @Body barbeiro: EditarBarbeiro): Response<Servico>
}