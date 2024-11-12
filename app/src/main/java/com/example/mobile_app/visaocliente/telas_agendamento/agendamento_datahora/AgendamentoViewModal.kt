package com.example.mobile_app.visaocliente.telas_agendamento.agendamento_datahora

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_app.login.UserLoginSession
import com.example.mobile_app.visaobarbeiro.api.RetrofitService
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.ApiBarbeiros
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.ver_barbeiro.componente.VerBarbeiro
import com.example.mobile_app.visaocliente.telas_editarPerfil.ApiDadosCliente
import com.example.mobile_app.visaocliente.telas_editarPerfil.DadosCliente
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AgendamentoViewModel : ViewModel() {

    var dadosCliente = mutableStateOf<DadosCliente?>(null)
    private val apiDadosCliente: ApiDadosCliente = RetrofitService.apiDadosCliente

    var barbeiroSelecionado by mutableStateOf<Long?>(null)
    var servicosSelecionados = mutableStateListOf<Long>()
    var dataSelecionada by mutableStateOf<LocalDateTime?>(null)

    init {
        carregarDadosCliente()
    }

    fun carregarDadosCliente() {
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
                        dadosCliente.value = null // Garantir que não temos dados incompletos
                    }
                } catch (e: Exception) {
                    Log.e("carregar perfil", "Erro ao carregar dados do cliente", e)
                    dadosCliente.value = null // Garantir que não temos dados incompletos
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

    fun confirmarAgendamento() {
        val barbeiroId = barbeiroSelecionado
        val clienteId = dadosCliente.value?.id
        val servicoIds = servicosSelecionados
        val inicio = dataSelecionada

        if (barbeiroId != null && clienteId != null && servicoIds.isNotEmpty() && inicio != null) {
            Log.d("Agendamento", "Cliente ID: $clienteId, Barbeiro ID: $barbeiroId, Data e hora: $inicio")
            salvarAgendamento(barbeiroId, clienteId, servicoIds, inicio)
        } else {
            Log.e("Agendamento", "Erro: Dados incompletos para o agendamento. Barbeiro: $barbeiroId, Cliente: $clienteId, Servicos: $servicoIds, Hora: $inicio")
        }
    }



    fun salvarAgendamento(barbeiroId: Long, clienteId: Long, servicoIds: List<Long>, inicio: LocalDateTime) {
        viewModelScope.launch {
            try {
                val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
                val inicioFormatado = inicio.format(formatter)
                Log.d("Agendamento", "Início formatado: $inicioFormatado")

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

    private val _barbeiros = mutableStateListOf<VerBarbeiro>()
    val barbeiros: List<VerBarbeiro> get() = _barbeiros


    private val apiBarbeiro: ApiBarbeiros = RetrofitService.apiBarbeiros

    init {
        fetchBarbeiros()
    }

    fun fetchBarbeiros() {
        viewModelScope.launch {
            try {
                val resposta = apiBarbeiro.get()
                Log.i("api", "Resposta da API: ${resposta.body()}")
                if (resposta.isSuccessful) {
                    resposta.body()?.let {
                        _barbeiros.clear()
                        _barbeiros.addAll(it)
                        Log.i("api", "Barbeiros carregados com sucesso: ${_barbeiros.size} barbeiros encontrados.")
                    }
                } else {
                }
            } catch (exception: Exception) {
                Log.e("api", "Erro ao buscar barbeiros: ${exception.message}")
            } finally {
            }
        }
    }

    private fun isNovo() = agendamentoAtual.id == null

    fun setAgendamentoParaEdicao(agendamento: Agendamento) {
        agendamentoAtual = agendamento
    }

    fun selecionarBarbeiro(barbeiroId: Long) {
        barbeiroSelecionado = barbeiroId
    }

    // Função para atualizar a lista de serviços selecionados
    fun selecionarServico(servicoId: Long) {
        if (servicosSelecionados.contains(servicoId)) {
            servicosSelecionados.remove(servicoId)
        } else {
            servicosSelecionados.add(servicoId)
        }
    }

    // Função para atualizar a data e hora do agendamento
    fun selecionarDataHora(dataHora: LocalDateTime) {
        dataSelecionada = dataHora
    }
}



