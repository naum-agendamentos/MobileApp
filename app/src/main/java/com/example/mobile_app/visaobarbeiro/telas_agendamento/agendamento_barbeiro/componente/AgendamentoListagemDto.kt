import java.time.LocalDateTime

data class AgendamentoListagemDto(
    val id: Long,
    val dataHoraAgendamento: LocalDateTime,
    val cliente: ClienteListagemDto,
    val barbeiro: BarbeiroListagemDto,
    val valorTotal: Double,
    val servicos: List<ServicoListagemDto> // Note que Servico foi trocado por ServicoListagemDto
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

    data class ServicoListagemDto(
        val id: Long,
        val nome: String,
        val preco: Double,
        val tempoServico: Int
    )
}