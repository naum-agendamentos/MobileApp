package com.example.mobile_app.visaobarbeiro.telas_servico

import com.example.mobile_app.visaobarbeiro.telas_servico.cadastrar_servico.componente.CadastrarServico
import com.example.mobile_app.visaobarbeiro.telas_servico.editar_servico.componente.EditarServico
import com.example.mobile_app.visaobarbeiro.telas_servico.ver_servicos.componente.Servico
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServicos {
    @GET("servicos")
    suspend fun get(
        @Query("idBarbearia") idBarbearia: Long): Response<List<Servico>>

    @GET("servicos/{id}")
    suspend fun getServicoPorId(@Path("id") servicoId: Long, @Query("idBarbearia") idBarbearia: Long): Response<List<Servico>>

    @POST("servicos")
    suspend fun cadastrar(@Body novoServico: CadastrarServico, @Query("idBarbearia") idBarbearia: Long): Response<Servico>

    @PUT("servicos/{id}")
    suspend fun editar(@Path("id") servicoId: Long, @Body servico: EditarServico): Response<Servico>
}