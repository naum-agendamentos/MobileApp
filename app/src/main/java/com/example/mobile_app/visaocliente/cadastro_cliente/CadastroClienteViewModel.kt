package com.example.mobile_app.visaocliente.cadastro_cliente

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

    fun cadastrarCliente(cliente: Cliente, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = apiCliente.cadastrarCliente(cliente)
                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    // Captura a mensagem de erro do corpo da resposta, se disponível
                    val errorBody = response.errorBody()?.string()
                    onError("Erro ao cadastrar cliente: ${errorBody ?: response.message()}")
                }
            } catch (e: IOException) {
                // Erro de rede ou de conversão
                onError("Erro de rede: ${e.message}")
            } catch (e: HttpException) {
                // Erro HTTP
                onError("Erro HTTP: ${e.message}")
            } catch (e: Exception) {
                // Outros erros
                onError("Erro desconhecido: ${e.message}")
            }
        }
    }
}
