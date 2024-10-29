package com.example.homepage.visaocliente.componentes.muralcomponentes

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_app.RetrofitService
import com.example.mobile_app.visaocliente.componentes.muralcomponentes.ApiMural
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MuralViewModel : ViewModel() {
    private val apiMural: ApiMural
    private val avisos = mutableStateListOf<Aviso>()

    var itemAtual by mutableStateOf(Aviso())

    init {
        apiMural = RetrofitService.getApiAviso
    }


    fun getAvisos(): List<Aviso> {
        viewModelScope.launch {
            try {
                val resposta = apiMural.get()
                Log.i("api", "Resposta da api ${resposta.body()}")
                if (resposta.isSuccessful) {
                    Log.i("api", "items da API: ${resposta.body()}")
                    avisos.clear()
                    resposta.body()
                        ?.let { avisos.addAll(it) } // Adiciona todos os avisos diretamente
                } else {
                    Log.e("api", "Erro ao buscar items: ${resposta.errorBody()?.string()}")
                }
            } catch (exception: Exception) {
                Log.e("api", "Erro ao buscar items: ,${exception}")
            }
        }
        return avisos.toList()
    }

    fun salvar() {
        viewModelScope.launch {
            try {
                val resposta = if (isNovo()) {
                    apiMural.post(itemAtual)
                } else {
                    apiMural.put(itemAtual.id!!, itemAtual)
                }

                if (resposta.isSuccessful) {
                    if (isNovo()) {
                        avisos.add(resposta.body()!!)
                    } else {
                        val posicaoDoAtual = avisos.indexOfFirst { it.id == itemAtual.id }
                        avisos[posicaoDoAtual] = resposta.body()!!
                    }
                } else {
                    Log.e("api", "Erro ao salvar aviso: ${resposta.errorBody()?.string()}")
                }
            } catch (exception: Exception) {
                Log.e("api", "Erro ao salvar aviso", exception)
            }
        }
    }

    fun isNovo() = itemAtual.id == null

    fun setItemParaEdicao(aviso: Aviso) {
        itemAtual = aviso
    }
}

