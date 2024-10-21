package com.example.mobile_app.visaobarbeiro.telas_servicos

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_app.visaobarbeiro.api.RetrofitService
import com.example.mobile_app.visaobarbeiro.telas_servicos.ver_servicos.componente.Servico
import kotlinx.coroutines.launch

class ServicosViewModel : ViewModel() {

    private val _servico = mutableStateListOf<Servico>()
    val servicos: List<Servico> get() = _servico

    var isLoading = mutableStateOf(true)
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set

    private val apiServico: ApiServicos = RetrofitService.apiServicos

    init {
        // Você pode inicializar com um valor padrão se desejar
        getServicos(1)
    }

    fun getServicos(idBarbearia: Long) {
        isLoading.value = true // Define loading como true antes da chamada
        viewModelScope.launch {
            try {
                val resposta = apiServico.get(idBarbearia) // Passando o idBarbearia
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
}
