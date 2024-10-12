package com.example.mobile_app.visaocliente.componentes.muralcomponentes

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.mobile_app.RetrofitService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MuralViewModel : ViewModel() {
    private val avisos = mutableStateListOf<Aviso>()
    private val apiMural : ApiMural

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
        return avisos
    }
}