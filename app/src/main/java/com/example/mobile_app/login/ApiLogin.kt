package com.example.mobile_app.login

import com.example.mobile_app.visaocliente.telas_editarPerfil.DadosCliente
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiLogin {
    @POST("usuarios/login")
    suspend fun login(@Body loginRequest: NovaSession): Response<SessionLogin>


}