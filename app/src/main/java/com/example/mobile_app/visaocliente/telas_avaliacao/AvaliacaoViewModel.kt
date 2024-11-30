package com.example.mobile_app.visaocliente.telas_avaliacao

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_app.login.UserLoginSession
import com.example.mobile_app.visaobarbeiro.api.RetrofitService
import com.example.mobile_app.visaocliente.telas_avaliacao.componentes.Avaliacao
import kotlinx.coroutines.launch

class AvaliacaoViewModel : ViewModel() {
    private val apiAvaliacao: ApiAvaliacao = RetrofitService.apiAvaliacao

    fun postAvaliacao(qtdEstrelas: Int, onSuccess: () -> Unit, onError: (String) -> Unit) {
        val idCliente = UserLoginSession.idCliente!!
        viewModelScope.launch {
            try {
                val avaliacao = Avaliacao(qtdEstrelas)
                Log.d("AvaliacaoViewModel", "Iniciando cadastro de avaliação: idCliente=$idCliente, idBarbearia=$1, qtdEstrelas=${avaliacao.qtdEstrela}")

                val response = apiAvaliacao.cadastrarAvaliacao(1L, idCliente, avaliacao)
                if (response.isSuccessful) {
                    Log.d("AvaliacaoViewModel", "Avaliação cadastrada com sucesso: $response")
                    onSuccess()
                } else {
                    Log.e("AvaliacaoViewModel", "Erro ao cadastrar avaliação: ${response.errorBody()}")
                    onError("Erro ao cadastrar avaliação: ${response.errorBody()}")
                }
            } catch (e: Exception) {
                Log.e("AvaliacaoViewModel", "Erro ao cadastrar avaliação", e)
                onError("Erro ao cadastrar avaliação: ${e.message}")
            }
        }
    }
}
