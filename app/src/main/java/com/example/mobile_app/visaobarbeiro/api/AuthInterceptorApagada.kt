package com.example.mobile_app.visaobarbeiro.api

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptorApagada(private val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val requestWithHeaders: Request = originalRequest.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()
        return chain.proceed(requestWithHeaders)
    }
}


