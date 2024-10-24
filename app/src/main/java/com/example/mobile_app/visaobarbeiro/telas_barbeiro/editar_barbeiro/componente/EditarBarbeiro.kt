package com.example.mobile_app.visaobarbeiro.telas_barbeiro.editar_barbeiro.componente

data class EditarBarbeiro(
    val nome: String,
    val email: String,
    val senha: String,
    val telefone: String,
    val descricao: String,
    val foto: String? = null

) {
}