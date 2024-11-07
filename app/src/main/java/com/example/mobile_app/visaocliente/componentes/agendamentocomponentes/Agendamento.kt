package com.example.homepage.visaocliente.componentes.muralcomponentes

import com.example.mobile_app.visaocliente.componentes.agendamentocomponentes.Barbeiro
import com.example.mobile_app.visaocliente.componentes.agendamentocomponentes.Cliente
import com.example.mobile_app.visaocliente.componentes.agendamentocomponentes.DadosCliente
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
