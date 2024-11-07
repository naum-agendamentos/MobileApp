package com.example.mobile_app.visaocliente.componentes.servicocomponente

data class Servico(
    var id: Long? = null,
    var nomeServico: String? = null,
    var preco: Double? = null,
    var tempoServico: Int? = null,
    var barbeariaId: Long? = null // ID da barbearia ao invés da entidade Barbearia
)
