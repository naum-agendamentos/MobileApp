package com.example.mobile_app.visaocliente.componentes.servicocomponente

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_app.visaobarbeiro.api.RetrofitService
import com.example.mobile_app.visaobarbeiro.api.RetrofitServiceApagada2
import com.example.mobile_app.visaobarbeiro.telas_servico.ApiServicos
import com.example.mobile_app.visaobarbeiro.telas_servico.cadastrar_servico.componente.CadastrarServico
import com.example.mobile_app.visaobarbeiro.telas_servico.editar_servico.componente.EditarServico
import kotlinx.coroutines.launch


class ServicosViewModelCliente : ViewModel() {

    private val _servico = mutableStateListOf<Servico>()
    val servicos: List<Servico> get() = _servico
    val servicosSelecionados = mutableListOf<Servico>()

    var isLoading = mutableStateOf(true)
        private set

    var isSubmitting = mutableStateOf(false)
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set

    private val apiServico: ApiServico = RetrofitService.apiServico

    init {
        getServicos(1)
    }


    fun getServicos(idBarbearia: Long) {
        isLoading.value = true // Define loading como true antes da chamada
        viewModelScope.launch {
            try {
                val resposta = apiServico.get(idBarbearia) // Passando o servicoId
                Log.i("api", "Resposta da API: ${resposta.body()}")
                if (resposta.isSuccessful) {
                    resposta.body()?.let {
                        _servico.clear()
                        _servico.addAll(it)
                        Log.i(
                            "api",
                            "Servicos carregados com sucesso: ${_servico.size} servicos encontrados."
                        )
                    }
                } else {
                    errorMessage.value = resposta.errorBody()?.string()
                    Log.e("api", "Erro ao buscar servicos: ${errorMessage.value}")
                }
            } catch (exception: Exception) {
                Log.e("api", "Erro ao buscar servicos: ${exception.message}")
                errorMessage.value = exception.message // Armazena mensagem de erro
            } finally {
                isLoading.value = false
            }
        }
    }

    fun addServicos(servico : Servico){
        servicosSelecionados.add(servico)
    }
}
