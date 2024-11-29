package com.example.mobile_app.visaobarbeiro.api

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private var token: String?) : Interceptor {
    private val TAG = "AuthInterceptor"

    @Synchronized
    fun setToken(newToken: String) {
        token = newToken
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        token?.let {
            Log.d(TAG, "Adicionando header Authorization: Bearer $it")
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(requestBuilder.build()).also { response ->
            if (!response.isSuccessful) {
                Log.e(TAG, "Erro na requisição: ${response.code} - ${response.message}")
            }
        }
    }
}