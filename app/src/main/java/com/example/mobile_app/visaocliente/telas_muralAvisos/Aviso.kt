package com.example.homepage.visaocliente.componentes.muralcomponentes

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Aviso(
    var id: Long? = null,
    var titulo: String? = null,
    var descricao: String? = null,
    var data: String? = null, // Mude para String
    var url: String? = null,
    var tipoAviso: String? = null,
    var barbeiroId: Long? = null
)
