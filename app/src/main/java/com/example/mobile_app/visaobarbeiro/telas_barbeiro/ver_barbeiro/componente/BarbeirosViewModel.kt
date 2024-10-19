package com.example.mobile_app.visaobarbeiro.telas_barbeiro.ver_barbeiro.componente

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.RetrofitService
import kotlinx.coroutines.launch

class BarbeirosViewModel : ViewModel() {
    private val _barbeiros = mutableStateListOf<BarbeiroListagemDto>()
    val barbeiros: List<BarbeiroListagemDto> get() = _barbeiros

    var isLoading = mutableStateOf(true)
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
}
