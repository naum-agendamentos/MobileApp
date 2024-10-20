package com.example.mobile_app.visaobarbeiro.telas_barbeiro.cadastrar_barbeiro.componente

data class CadastrarBarbeiro(
    val nome: String,
    val email: String,
    val senha: String,
    val telefone: String,
    val descricao: String,
    val foto: String? = "https://res-console.cloudinary.com/dmgfyyioo/media_explorer_thumbnails/3745a8c61a7eb36c0113035e895e85e6/detailed"

) {
}