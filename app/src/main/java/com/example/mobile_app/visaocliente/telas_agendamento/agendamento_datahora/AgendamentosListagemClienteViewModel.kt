package com.example.mobile_app.visaocliente.telas_agendamento

import AgendamentoListagemDto
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_app.visaobarbeiro.api.RetrofitService
import com.example.mobile_app.visaobarbeiro.api.RetrofitService.apiAgendamentoCliente
import kotlinx.coroutines.launch
import java.time.LocalDate

class AgendamentosListagemClienteViewModel : ViewModel() {
    private val _agendamentos = mutableStateListOf<AgendamentoListagemDto>()
    val agendamentos: List<AgendamentoListagemDto> get() = _agendamentos

    var isLoading = mutableStateOf(true)
        private set

    var isSubmitting = mutableStateOf(false)
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set

    private val apiAgendamentosCliente = RetrofitService.apiAgendamentosCliente

    // Busca agendamentos com base no clienteId
    fun getAgendamentosPorCliente(clienteId: Long) {
        isLoading.value = true
        viewModelScope.launch {
            try {
                val resposta = apiAgendamentoCliente.getAgendamentosPorCliente(clienteId)

                // Log detalhado da resposta
                Log.i("api", "Status Code: ${resposta.code()}")
                Log.i("api", "Resposta: ${resposta.body()}")
                Log.i("api", "Erro: ${resposta.errorBody()?.string()}")

                if (resposta.isSuccessful) {
                    resposta.body()?.let { agendamentosDtoList ->
                        // Atualiza a lista de agendamentos
                        _agendamentos.clear()
                        _agendamentos.addAll(agendamentosDtoList)
                    }
                } else {
                    // Trata o erro retornado pelo servidor
                    errorMessage.value = "Erro do servidor: ${resposta.errorBody()?.string()}"
                    Log.e("api", "Erro ao carregar agendamentos: ${errorMessage.value}")
                }
            } catch (exception: Exception) {
                // Trata exceções locais (como problemas de rede)
                errorMessage.value = "Erro ao buscar agendamentos: ${exception.localizedMessage}"
                Log.e("api", "Exceção: ${exception.localizedMessage}", exception)
            } finally {
                // Finaliza o carregamento
                isLoading.value = false
            }
        }
    }


    // Filtra os agendamentos pela data selecionada
    fun getAgendamentosPorData(data: LocalDate): List<AgendamentoListagemDto> {
        return _agendamentos.filter {
            val dataAgendamento = LocalDate.parse(it.dataHoraAgendamento.substring(0, 10))
            dataAgendamento.isEqual(data)
        }
    }
}
