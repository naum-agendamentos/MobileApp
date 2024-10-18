package com.example.homepage.visaocliente.componentes.muralcomponentes

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mobile_app.RetrofitService
import com.example.mobile_app.visaocliente.componentes.muralcomponentes.ApiMural
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MuralViewModel : ViewModel() {
    private val avisos = mutableStateListOf<Aviso>()
    private val apiMural : ApiMural
    var itemAtual by mutableStateOf(Aviso())

    init {
        apiMural = RetrofitService.getApiAviso()
    }

    //    init {
//        avisos.add(Aviso("Aviso 1", "Descrição do Aviso 1"))
//        avisos.add(Aviso("Aviso 2", "Descrição do Aviso 2"))
//    }
    fun getAvisos() : List<Aviso> {
        GlobalScope.launch {
            try {
                val resposta = apiMural.get()
                Log.i("api", "Resposta da api ${resposta.body()}")
                if (resposta.isSuccessful) {
                    Log.i("api", "items da API: ${resposta.body()}")
                    avisos.clear()
                    avisos.addAll(resposta.body() ?: emptyList())
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
        GlobalScope.launch {
            try {
                if(isNovo()){
                    val resposta = apiMural.post(itemAtual)

                    if(resposta.isSuccessful){
                        avisos.add(itemAtual)
                    } else{
                        Log.e("api", "Erro ao cadastrar aviso: ${resposta.errorBody()?.string()}")
                    }
                } else{
                    val resposta = apiMural.put(itemAtual.id!!, itemAtual)

                    if(resposta.isSuccessful){
                        val posicaoDoAtual = avisos.indexOfFirst { it.id == itemAtual.id }
                        avisos[posicaoDoAtual] = resposta.body()!!
                    } else{
                        Log.e("api", "Erro ao atualizar aviso: ${resposta.errorBody()?.string()}")
                    }
                }
            } catch (exception: Exception){
                Log.e("api", "Erro ao atualizar aviso", exception)
            }
        }
    }

    fun isNovo() = itemAtual.id == null

    fun setItemParaEdicao(aviso: Aviso) {
        itemAtual = aviso
    }
}