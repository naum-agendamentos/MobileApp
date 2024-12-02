package com.example.mobile_app.visaobarbeiro.telas_dashboard

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_app.visaobarbeiro.api.RetrofitService
import com.example.mobile_app.visaobarbeiro.telas_dashboard.componentes.BarbeiroLucroListagemDto
import com.example.mobile_app.visaobarbeiro.telas_dashboard.componentes.BarbeiroQtdCortesListagemDto
import com.example.mobile_app.visaobarbeiro.telas_dashboard.componentes.ServicoQtdMesListagemDto
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    private val apiDashboard: ApiDashboard = RetrofitService.apiDashboard

    var errorMessage = mutableStateOf<String?>(null)
        private set

    private val _barbeiros = mutableStateListOf<BarbeiroQtdCortesListagemDto>()
    val barbeiros: List<BarbeiroQtdCortesListagemDto> get() = _barbeiros

    private val _lucroBarbeiros = mutableStateListOf<BarbeiroLucroListagemDto>()
    val lucroBarbeiros: List<BarbeiroLucroListagemDto> get() = _lucroBarbeiros

    private val _topServicos = mutableStateListOf<ServicoQtdMesListagemDto>()
    val topServicos: List<ServicoQtdMesListagemDto> get() = _topServicos

    var lucro = mutableStateOf<Double?>(null)
        private set

    var totalAgendamentosHoje = mutableStateOf<Int?>(null)
        private set

    var mediaAvaliacao = mutableStateOf<Double?>(null)
        private set

    var porcAgendOntemHj = mutableStateOf<Double?>(null)
        private set



    fun barbeiroQtdCortes() {
        viewModelScope.launch {
            try {
                val resposta = apiDashboard.getCortePorBarbeiro()
                Log.i("apiDashboard", "Resposta da API: ${resposta.body()}")
                if (resposta.isSuccessful) {
                    resposta.body()?.let { lista ->
                        _barbeiros.clear()
                        _barbeiros.addAll(lista)
                        Log.i("apiDashboard", "Quantidades cortes barbeiros: ${_barbeiros.size}.")
                    }
                } else {
                    errorMessage.value = resposta.errorBody()?.string()
                    Log.e("apiDashboard", "Erro ao buscar Qtd Corte barbeiros: ${errorMessage.value}")
                }
            } catch (exception: Exception) {
                Log.e("apiDashboard", "Erro ao buscar qtd Corte barbeiros: ${exception.message}")
                errorMessage.value = exception.message
            }
        }
    }

    fun lucroPorBarbeiro() {
        viewModelScope.launch {
            try {
                val resposta = apiDashboard.getLucroPorBarbeiro()
                Log.i("apiDashboard", "Resposta da API: ${resposta.body()}")
                if (resposta.isSuccessful) {
                    resposta.body()?.let { lista ->
                        _lucroBarbeiros.clear()
                        _lucroBarbeiros.addAll(lista)
                        Log.i("apiDashboard", "Lucros barbeiros: ${_lucroBarbeiros.size}.")
                    }
                } else {
                    errorMessage.value = resposta.errorBody()?.string()
                    Log.e("apiDashboard", "Erro ao buscar Lucro barbeiros: ${errorMessage.value}")
                }
            } catch (exception: Exception) {
                Log.e("apiDashboard", "Erro ao buscar lucro barbeiros: ${exception.message}")
                errorMessage.value = exception.message
            }
        }
    }

    fun topServicos() {
        viewModelScope.launch {
            try {
                val resposta = apiDashboard.getTopServicos()
                Log.i("apiDashboard", "Resposta da API: ${resposta.body()}")
                if (resposta.isSuccessful) {
                    resposta.body()?.let { lista ->
                        _topServicos.clear()
                        _topServicos.addAll(lista)
                        Log.i("apiDashboard", "Top serviços: ${_topServicos.size}.")
                    }
                } else {
                    errorMessage.value = resposta.errorBody()?.string()
                    Log.e("apiDashboard", "Erro ao buscar Top serviços: ${errorMessage.value}")
                }
            } catch (exception: Exception) {
                Log.e("apiDashboard", "Erro ao buscar top serviços: ${exception.message}")
                errorMessage.value = exception.message
            }
        }
    }

    fun obterLucro() {
        viewModelScope.launch {
            try {
                val resposta = apiDashboard.getLucro()
                Log.i("apiDashboard", "Resposta da API: ${resposta.body()}")
                if (resposta.isSuccessful) {
                    lucro.value = resposta.body()
                } else {
                    errorMessage.value = resposta.errorBody()?.string()
                    Log.e("apiDashboard", "Erro ao buscar Lucro: ${errorMessage.value}")
                }
            } catch (exception: Exception) {
                Log.e("apiDashboard", "Erro ao buscar lucro: ${exception.message}")
                errorMessage.value = exception.message
            }
        }
    }

    fun totalAgendamentosHoje() {
        viewModelScope.launch {
            try {
                val resposta = apiDashboard.geTotalAgendDia()
                Log.i("apiDashboard", "Resposta da API: ${resposta.body()}")
                if (resposta.isSuccessful) {
                    totalAgendamentosHoje.value = resposta.body()
                } else {
                    errorMessage.value = resposta.errorBody()?.string()
                    Log.e("apiDashboard", "Erro ao buscar Total agendamentos hoje: ${errorMessage.value}")
                }
            } catch (exception: Exception) {
                Log.e("apiDashboard", "Erro ao buscar total agendamentos hoje: ${exception.message}")
                errorMessage.value = exception.message
            }
        }
    }

    fun porcentagemAgendOntemHoje() {
        viewModelScope.launch {
            try {
                val resposta = apiDashboard.getPorcAgendamentoOntemHoje()
                Log.i("apiDashboard", "Resposta da API porcentagem: ${resposta.body()}")
                if (resposta.isSuccessful) {
                    porcAgendOntemHj.value = resposta.body()
                } else {
                    errorMessage.value = resposta.errorBody()?.string()
                    Log.e("apiDashboard", "Erro ao buscar porcentagem agendamentos dia: ${errorMessage.value}")
                }
            } catch (exception: Exception) {
                Log.e("apiDashboard", "Erro ao porcentagem agendamentos dia: ${exception.message}")
                errorMessage.value = exception.message
            }
        }
    }

    fun obterMediaAvaliacao() {
        viewModelScope.launch {
            try {
                val resposta = apiDashboard.getMediaAvaliacao(1L)
                Log.i("apiDashboard", "Resposta da API: ${resposta.body()}")
                if (resposta.isSuccessful) {
                    mediaAvaliacao.value = resposta.body()
                } else {
                    errorMessage.value = resposta.errorBody()?.string()
                    Log.e("apiDashboard", "Erro ao buscar Média Avaliação: ${errorMessage.value}")
                }
            } catch (exception: Exception) {
                Log.e("apiDashboard", "Erro ao buscar média avaliação: ${exception.message}")
                errorMessage.value = exception.message
            }
        }
    }
}

