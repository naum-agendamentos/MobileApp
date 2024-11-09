package com.example.mobile_app.visaobarbeiro.telas_barbeiro

//import com.example.mobile_app.visaobarbeiro.telas_barbeiro.bloqueio_de_dia.componente.SemanaEntity
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.cadastrar_barbeiro.componente.CadastrarBarbeiro
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.editar_barbeiro.componente.EditarBarbeiro
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.ver_barbeiro.componente.VerBarbeiro
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiBarbeiros {
    @GET("barbeiros")
    suspend fun get(): Response<List<VerBarbeiro>>

    @GET("barbeiros/{id}")
    suspend fun getBarbeiroPorId(@Path("id") barbeiroId: Long): Response<List<VerBarbeiro>>

    @POST("barbeiros")
    suspend fun cadastrar(@Body novoBarbeiro: CadastrarBarbeiro): Response<VerBarbeiro>

    @PUT("barbeiros/{id}")
    suspend fun editar(@Path("id") barbeiroId: Long, @Body barbeiro: EditarBarbeiro): Response<VerBarbeiro>

//    @PUT("/semana/{idBarbeiro}")
//    suspend fun putBarbeiros(@Path("idBarbeiro") idBarbeiro: Long, @Body novaSemana: SemanaEntity): Response<SemanaEntity>
}
