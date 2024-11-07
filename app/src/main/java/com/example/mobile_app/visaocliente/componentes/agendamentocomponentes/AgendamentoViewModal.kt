package com.example.mobile_app.visaocliente.componentes.agendamentocomponentes

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homepage.visaocliente.componentes.muralcomponentes.Agendamento
import com.example.mobile_app.login.UserLoginSession
import com.example.mobile_app.visaobarbeiro.api.RetrofitService
import com.example.mobile_app.visaocliente.componentes.meuperfilcomponentes.ApiDadosCliente
import com.example.mobile_app.visaocliente.componentes.meuperfilcomponentes.DadosCliente
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AgendamentoViewModel : ViewModel() {

    var dadosCliente = mutableStateOf<DadosCliente?>(null)
    private val apiDadosCliente: ApiDadosCliente = RetrofitService.apiDadosCliente

    init {
        carregarDadosCliente()
    }

    private fun carregarDadosCliente() {
        UserLoginSession.userId?.let { userId ->
            viewModelScope.launch {
                try {
                    val response = apiDadosCliente.getDadosCliente(userId)
                    Log.d("carregar perfil", "ID: ${UserLoginSession.userId}")
                    Log.d("carregar perfil", "Resposta da API: $response")
                    if (response.isSuccessful) {
                        dadosCliente.value = response.body()
                        UserLoginSession.email = dadosCliente.value?.email
                    } else {
                        Log.e("carregar perfil", "Erro na resposta: ${response.code()}")
                    }
                } catch (e: Exception) {
                    Log.e("carregar perfil", "Erro ao carregar dados do cliente", e)
                }
            }
        } ?: Log.e("carregar perfil", "userId é nulo")
    }

    private val apiAgendamento = RetrofitService.apiAgendamentoCliente
    private val agendamentos = mutableStateListOf<Agendamento>()

    var agendamentoAtual by mutableStateOf(Agendamento())

    private fun buscarAgendamentos() {
        val clienteId = dadosCliente.value?.id ?: return

        viewModelScope.launch {
            try {
                val response = apiAgendamento.listarAgendamentosPorCliente(clienteId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        agendamentos.clear()
                        agendamentos.addAll(it)
                    }
                } else {
                    Log.e("api", "Erro ao listar agendamentos: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("api", "Erro ao listar agendamentos", e)
            }
        }
    }

    fun buscarAgendamento(id: Long) {
        viewModelScope.launch {
            try {
                val resposta = apiAgendamento.buscarAgendamento(id)
                if (resposta.isSuccessful) {
                    agendamentoAtual = resposta.body() ?: Agendamento()
                } else {
                    Log.e("api", "Erro ao buscar agendamento: ${resposta.errorBody()?.string()}")
                }
            } catch (exception: Exception) {
                Log.e("api", "Erro ao buscar agendamento", exception)
            }
        }
    }

    fun salvarAgendamento(barbeiroId: Long, clienteId: Long, servicoIds: List<Long>, inicio: LocalDateTime) {
        viewModelScope.launch {
            try {
                val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
                val inicioFormatado = inicio.format(formatter)

                val resposta = if (isNovo()) {
                    apiAgendamento.criarAgendamento(barbeiroId, clienteId, servicoIds, inicioFormatado)
                } else {
                    apiAgendamento.atualizarAgendamento(agendamentoAtual.id!!, barbeiroId, clienteId, servicoIds, inicioFormatado)
                }

                if (resposta.isSuccessful) {
                    resposta.body()?.let {
                        if (isNovo()) {
                            agendamentos.add(it)
                        } else {
                            val posicao = agendamentos.indexOfFirst { ag -> ag.id == agendamentoAtual.id }
                            if (posicao >= 0) agendamentos[posicao] = it
                        }
                        agendamentoAtual = Agendamento()
                    }
                } else {
                    Log.e("api", "Erro ao salvar agendamento: ${resposta.errorBody()?.string()}")
                }
            } catch (exception: Exception) {
                Log.e("api", "Erro ao salvar agendamento", exception)
            }
        }
    }

    fun excluirAgendamento(id: Long) {
        viewModelScope.launch {
            try {
                val resposta = apiAgendamento.excluirAgendamento(id)
                if (resposta.isSuccessful) {
                    agendamentos.removeIf { it.id == id }
                } else {
                    Log.e("api", "Erro ao excluir agendamento: ${resposta.errorBody()?.string()}")
                }
            } catch (exception: Exception) {
                Log.e("api", "Erro ao excluir agendamento", exception)
            }
        }
    }

    private fun isNovo() = agendamentoAtual.id == null

    fun setAgendamentoParaEdicao(agendamento: Agendamento) {
        agendamentoAtual = agendamento
    }
}



