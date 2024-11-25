package com.example.mobile_app.visaocliente.cadastro_cliente

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_app.visaobarbeiro.api.RetrofitService
import com.example.mobile_app.visaocliente.telas_agendamento.agendamento_datahora.Cliente
import com.example.mobile_app.visaocliente.telas_editarPerfil.ApiDadosCliente
import kotlinx.coroutines.launch

class CadastroClienteViewModel : ViewModel(){

    private  val apiCliente: ApiDadosCliente  = RetrofitService.apiDadosCliente
    fun cadastrarCliente(cliente: Cliente, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = apiCliente.cadastrarCliente(cliente)
                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    onError("Erro ao cadastrar cliente: ${response.message()}")
                }
            } catch (e: Exception) {
                onError(e.message ?: "Erro desconhecido")
            }
        }
    }
}
