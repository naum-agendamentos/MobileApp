package com.example.mobile_app.visaobarbeiro.telas_agendamento

import AgendamentoListagemDto
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_app.visaobarbeiro.api.RetrofitService
import com.example.mobile_app.visaobarbeiro.telas_servico.ApiServicos
import com.example.mobile_app.visaobarbeiro.telas_servico.ver_servicos.componente.Servico
import kotlinx.coroutines.launch

class AgendamentosViewModel : ViewModel() {
    private val _agendamentos = mutableStateListOf<AgendamentoListagemDto>()
    val agendamentos: List<AgendamentoListagemDto> get() = _agendamentos

    var isLoading = mutableStateOf(true)
        private set

    var isSubmitting = mutableStateOf(false)
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set

    private val apiAgendamentos: ApiAgendamentos = RetrofitService.apiAgendamentos

    fun getAgendamentos(barbeiroId: Long) {
        isLoading.value = true // Define loading como true antes da chamada
        viewModelScope.launch {
            try {
                val resposta = apiAgendamentos.getAgendamentoPorId(barbeiroId)
                Log.i("api", "Resposta da API: ${resposta.body()}")
                if (resposta.isSuccessful) {
                    resposta.body()?.let {
                        _agendamentos.clear()
                        _agendamentos.addAll(it)
                        Log.i(
                            "api",
                            "Agendamentos carregados com sucesso: ${_agendamentos.size} agendamentos encontrados."
                        )
                    }
                } else {
                    errorMessage.value = resposta.errorBody()?.string()
                    Log.e("api", "Erro ao buscar Agendamentos: ${errorMessage.value}")
                }
            } catch (exception: Exception) {
                Log.e("api", "Erro ao buscar Agendamentos: ${exception.message}")
                errorMessage.value = exception.message // Armazena mensagem de erro
            } finally {
                isLoading.value = false
            }
        }
    }
}
