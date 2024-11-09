package com.example.mobile_app.visaocliente.telas_agendamento.agendamento_datahora

import java.time.LocalDateTime

data class Agendamento(
    val id: Long? = null,
    val barbeiro: Barbeiro? = null,
    val servicosIds: List<Long>? = null,
    val dadosCliente: DadosCliente? = null,
    val inicio: LocalDateTime? = null,
    val fim: LocalDateTime? = null,
    val valorTotal: Double? = null
)
