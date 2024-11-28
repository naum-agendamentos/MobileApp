package com.example.mobile_app.visaocliente.telas_agendamento.agendamento_datahora

import com.example.mobile_app.visaobarbeiro.telas_servico.ver_servicos.componente.Servico

data class AgendamentoDto(
    val id: Long,
    val dataHoraAgendamento: String,
    val cliente: ClienteListagemDto,
    val barbeiro: BarbeiroListagemDto,
    val valorTotal: Double,
    val servicos: List<Servico>
) {
    data class ClienteListagemDto(
        val id: Long,
        val email: String,
        val nome: String,
        val telefone: String
    )

    data class BarbeiroListagemDto(
        val id: Long,
        val nome: String,
        val email: String,
        val telefone: String,
        val foto: String
    )

}
