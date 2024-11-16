package com.example.mobile_app.visaobarbeiro.telas_barbeiro.ver_barbeiro.componente

import com.example.mobile_app.visaobarbeiro.telas_barbeiro.bloqueio_de_dia.componente.SemanaEntity

data class VerBarbeiro(
    val id: Long,
    val nome: String,
    val email: String,
    val senha: String,
    val telefone: String,
    val descricao: String,
    val foto: String?,
    val semana: SemanaEntity?
)
