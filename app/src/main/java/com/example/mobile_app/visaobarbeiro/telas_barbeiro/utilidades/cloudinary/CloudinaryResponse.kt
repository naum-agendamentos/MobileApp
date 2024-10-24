package com.example.mobile_app.visaobarbeiro.telas_barbeiro.utilidades.cloudinary

import com.google.gson.annotations.SerializedName

data class CloudinaryResponse(
    @SerializedName("secure_url")
    val secureUrl: String
)