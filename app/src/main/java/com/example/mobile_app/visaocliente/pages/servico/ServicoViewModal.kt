package com.example.mobile_app.visaocliente.pages.servico

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_app.visaobarbeiro.api.RetrofitService
import com.example.mobile_app.visaocliente.componentes.servicocomponente.Servico
import kotlinx.coroutines.launch
import java.io.Console

class ServicoViewModel : ViewModel() {
    private val apiServico = RetrofitService.apiServico

    private val servicos = mutableStateListOf<Servico>()

    var servicoSelecionado by mutableStateOf<Servico?>(null)

    init {
        carregarServicos()
    }

    fun getServicos(): List<Servico> = servicos.toList()

    fun carregarServicos() {
        viewModelScope.launch {
            try {
                val resposta = apiServico.listarServicos(1)
                Log.i("Buscar serviços", "resposta carregar servico ${resposta}")
                if (resposta.isSuccessful) {
                    servicos.clear()
                    resposta.body()?.let { servicos.addAll(it) }
                    Log.d("Buscar serviços", "Lista de serviços carregada: ${servicos}")
                }
            } catch (exception: Exception) {
                Log.e("Buscar serviços", "Erro ao carregar serviços: ${exception.message}")
            }
        }
    }


    fun selecionarServico(servico: Servico) {
        servicoSelecionado = if (servicoSelecionado == servico) null else servico
    }
}
