package com.example.mobile_app.visaobarbeiro.telas_barbeiro

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.cadastrar_barbeiro.componente.CadastrarBarbeiro
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.ver_barbeiro.componente.VerBarbeiro
import kotlinx.coroutines.launch

class BarbeirosViewModel : ViewModel() {
    private val _barbeiros = mutableStateListOf<VerBarbeiro>()
    val barbeiros: List<VerBarbeiro> get() = _barbeiros

    var isLoading = mutableStateOf(true)
        private set

    var isSubmitting = mutableStateOf(false)
        private set

    private val apiBarbeiro: ApiBarbeiros = RetrofitService.apiBarbeiros

    init {
        fetchBarbeiros()
    }

    private fun fetchBarbeiros() {
        viewModelScope.launch {
            try {
                val resposta = apiBarbeiro.get()
                Log.i("api", "Resposta da API: ${resposta.body()}")
                if (resposta.isSuccessful) {
                    resposta.body()?.let {
                        _barbeiros.clear()
                        _barbeiros.addAll(it)
                    }
                } else {
                    Log.e("api", "Erro ao buscar items: ${resposta.errorBody()?.string()}")
                }
            } catch (exception: Exception) {
                Log.e("api", "Erro ao buscar items: ${exception}")
            } finally {
                isLoading.value = false
            }
        }
    }

    fun cadastrarBarbeiro(novoBarbeiro: CadastrarBarbeiro) {
        viewModelScope.launch {
            isSubmitting.value = true // Indica que o envio está em progresso
            try {
                val resposta = apiBarbeiro.cadastrar(novoBarbeiro) // Chama a API para cadastrar o barbeiro
                Log.i("api", "Resposta da API: ${resposta.body()}")
                if (resposta.isSuccessful) {
                    resposta.body()?.let {
                        _barbeiros.add(it) // Adiciona o novo barbeiro à lista
                    }
                } else {
                    Log.e("api", "Erro ao cadastrar barbeiro: ${resposta.errorBody()?.string()}")
                }
            } catch (exception: Exception) {
                Log.e("api", "Erro ao cadastrar barbeiro: ${exception}")
            } finally {
                isSubmitting.value = false // Indica que o envio foi concluído
            }
        }
    }
}
