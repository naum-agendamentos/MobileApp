package com.example.mobile_app.login

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiLogin {
    @POST("usuarios/login")
    suspend fun login(@Body loginRequest: NovaSession): Response<SessionLogin>

}