package com.example.mobile_app.visaobarbeiro.telas_barbeiro

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_app.visaobarbeiro.api.RetrofitServiceApagada2
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.cadastrar_barbeiro.componente.CadastrarBarbeiro
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.editar_barbeiro.componente.EditarBarbeiro
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.ver_barbeiro.componente.VerBarbeiro
import kotlinx.coroutines.launch

class BarbeirosViewModel : ViewModel() {
    private val _barbeiros = mutableStateListOf<VerBarbeiro>()
    val barbeiros: List<VerBarbeiro> get() = _barbeiros

    var isLoading = mutableStateOf(true)
        private set

    var isSubmitting = mutableStateOf(false)
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set

    private val apiBarbeiro: ApiBarbeiros = RetrofitServiceApagada2.apiBarbeiros

    init {
        fetchBarbeiros()
    }

    fun fetchBarbeiros() {
        isLoading.value = true // Define loading como true antes da chamada
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
                    errorMessage.value = resposta.errorBody()?.string()
                    Log.e("api", "Erro ao buscar barbeiros: ${errorMessage.value}")
                }
            } catch (exception: Exception) {
                Log.e("api", "Erro ao buscar barbeiros: ${exception.message}")
                errorMessage.value = exception.message // Armazena mensagem de erro
            } finally {
                isLoading.value = false
            }
        }
    }

    fun cadastrarBarbeiro(novoBarbeiro: CadastrarBarbeiro) {
        viewModelScope.launch {
            isSubmitting.value = true
            try {
                val resposta = apiBarbeiro.cadastrar(novoBarbeiro)
                Log.i("api", "Resposta da API: ${resposta.body()}")
                if (resposta.isSuccessful) {
                    resposta.body()?.let {
                        _barbeiros.add(it)
                    }
                } else {
                    Log.e("api", "Erro ao cadastrar barbeiro: ${resposta.errorBody()?.string()}")
                }
            } catch (exception: Exception) {
                Log.e("api", "Erro ao cadastrar barbeiro: ${exception}")
            } finally {
                isSubmitting.value = false
            }
        }
    }

    fun editarBarbeiro(barbeiroId: Long, editarBarbeiro: EditarBarbeiro) {
        viewModelScope.launch {
            Log.d("ViewModel", "Tentando editar barbeiro com ID: $barbeiroId")
            isSubmitting.value = true
            try {
                val resposta = apiBarbeiro.editar(barbeiroId, editarBarbeiro)
                Log.i("api", "Resposta da API: ${resposta.body()}")
                if (resposta.isSuccessful) {
                    Log.d("ViewModel", "Barbeiro editado com sucesso!")
                    resposta.body()?.let {
                        // Atualiza a lista de barbeiros com o barbeiro editado
                        val index = _barbeiros.indexOfFirst { it.id == it.id } // Compare com it.id
                        if (index != -1) {
                            _barbeiros[index] = it // Atualiza o barbeiro existente
                        } else {
                            _barbeiros.add(it) // Adiciona um novo barbeiro caso não exista
                        }
                    }
                } else {
                    Log.e("api", "Erro ao editar barbeiro: ${resposta.errorBody()?.string()}")
                }
            } catch (exception: Exception) {
                Log.e("api", "Erro ao editar barbeiro: ${exception}")
            } finally {
                isSubmitting.value = false
            }
        }
    }




}

