package com.example.mobile_app.visaobarbeiro.telas_barbeiro.cadastrar_barbeiro.componente

data class CadastrarBarbeiro(
    val nome: String,
    val email: String,
    val senha: String,
    val telefone: String,
    val descricao: String,
    val foto: String? = null

) {
}