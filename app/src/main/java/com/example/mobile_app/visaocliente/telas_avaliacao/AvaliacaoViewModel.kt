package com.example.mobile_app.visaocliente.telas_avaliacao

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_app.visaobarbeiro.api.RetrofitService
import com.example.mobile_app.visaocliente.telas_agendamento.servicos_agendamento.ApiServico
import kotlinx.coroutines.launch

class AvaliacaoViewModel : ViewModel() {
    private val apiAvaliacao: ApiAvaliacao = RetrofitService.apiAvaliacao

    fun postAvaliacao(qtdEstrelas: Int) {
        viewModelScope.launch {
            apiAvaliacao.cadastrarAvaliacao(1, qtdEstrelas)
        }
    }

}