package com.example.mobile_app.visaobarbeiro.telas_barbeiro.ver_barbeiro.componente

data class VerBarbeiro(
    val id: Long,
    val nome: String,
    val email: String,
    val senha: String,
    val telefone: String,
    val descricao: String,
    val foto: String?
)
