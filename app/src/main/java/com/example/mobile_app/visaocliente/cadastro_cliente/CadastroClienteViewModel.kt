package com.example.mobile_app.visaocliente.cadastro_cliente

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_app.visaobarbeiro.api.RetrofitService
import com.example.mobile_app.visaocliente.telas_agendamento.agendamento_datahora.Cliente
import com.example.mobile_app.visaocliente.telas_editarPerfil.ApiDadosCliente
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class CadastroClienteViewModel : ViewModel() {

    private val apiCliente: ApiDadosCliente = RetrofitService.apiDadosCliente
    private val TAG = "CadastroClienteViewModel"

    fun cadastrarCliente(cliente: Cliente, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                Log.d(TAG, "Iniciando o cadastro do cliente: $cliente")
                val response = apiCliente.cadastrarCliente(cliente)
                if (response.isSuccessful) {
                    Log.d(TAG, "Cliente cadastrado com sucesso: ${response.body()}")
                    onSuccess()
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e(TAG, "Erro ao cadastrar cliente: ${errorBody ?: response.message()}")
                    onError("Erro ao cadastrar cliente: ${errorBody ?: response.message()}")
                }
            } catch (e: IOException) {
                Log.e(TAG, "Erro de rede: ${e.message}", e)
                onError("Erro de rede: ${e.message}")
            } catch (e: HttpException) {
                Log.e(TAG, "Erro HTTP: ${e.message}", e)
                onError("Erro HTTP: ${e.message}")
            } catch (e: Exception) {
                Log.e(TAG, "Erro desconhecido: ${e.message}", e)
                onError("Erro desconhecido: ${e.message}")
            }
        }
    }
}
