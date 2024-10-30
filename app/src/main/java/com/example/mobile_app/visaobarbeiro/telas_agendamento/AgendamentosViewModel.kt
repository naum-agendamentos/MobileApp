package com.example.mobile_app.visaobarbeiro.telas_agendamento

import AgendamentoListagemDto
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_app.visaobarbeiro.api.RetrofitServiceApagada2
import kotlinx.coroutines.launch
import java.time.LocalDate

class AgendamentosViewModel : ViewModel() {
    private val _agendamentos = mutableStateListOf<AgendamentoListagemDto>()
    val agendamentos: List<AgendamentoListagemDto> get() = _agendamentos

    var isLoading = mutableStateOf(true)
        private set

    var isSubmitting = mutableStateOf(false)
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set

    private val apiAgendamentos: ApiAgendamentos = RetrofitServiceApagada2.apiAgendamentos

    fun getAgendamentos(barbeiroId: Long) {
        isLoading.value = true
        viewModelScope.launch {
            try {
                val resposta = apiAgendamentos.getAgendamentoPorId(barbeiroId)
                Log.i("api", "Resposta da API: ${resposta.body()}")
                if (resposta.isSuccessful) {
                    resposta.body()?.let { agendamentosDtoList ->
                        _agendamentos.clear()
                        _agendamentos.addAll(agendamentosDtoList)
                    }
                } else {
                    errorMessage.value = resposta.errorBody()?.string()
                }
            } catch (exception: Exception) {
                errorMessage.value = exception.message
            } finally {
                isLoading.value = false
            }
        }
    }

    fun getAgendamentosPorData(data: LocalDate): List<AgendamentoListagemDto> {
        return _agendamentos.filter {
            val dataAgendamento = LocalDate.parse(it.dataHoraAgendamento.substring(0, 10))
            dataAgendamento.isEqual(data)
        }
    }
}

