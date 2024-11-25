import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_app.login.UserLoginSession
import kotlinx.coroutines.launch
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.mobile_app.visaobarbeiro.api.RetrofitService
import com.example.mobile_app.visaocliente.telas_editarPerfil.ApiDadosCliente
import com.example.mobile_app.visaocliente.telas_editarPerfil.DadosCliente

class EditPerfilViewModel : ViewModel() {
    var dadosCliente = mutableStateOf<DadosCliente?>(null)
    private val apiDadosCliente: ApiDadosCliente = RetrofitService.apiDadosCliente

    init {
        carregarDadosCliente()
    }

    private fun carregarDadosCliente() {
        UserLoginSession.userId?.let { userId ->
            viewModelScope.launch {
                try {
                    val response = apiDadosCliente.getDadosCliente(userId)
                    Log.d("carregar perfil", "ID: ${UserLoginSession.userId}")
                    Log.d("carregar perfil", "Resposta da API: $response")
                    if (response.isSuccessful) {
                        dadosCliente.value = response.body()
                    } else {
                        Log.e("carregar perfil", "Erro na resposta: ${response.code()}")
                    }
                } catch (e: Exception) {
                    Log.e("carregar perfil", "Erro ao carregar dados do cliente", e)
                }
            }
        } ?: Log.e("carregar perfil", "userId é nulo")
    }

    // ViewModel
     fun excluirCliente(onSuccess: () -> Unit) {
        UserLoginSession.idCliente?.let { userId ->
            viewModelScope.launch {
                try {
                    val response = apiDadosCliente.deleteDadosCliente(userId)
                    if (response.isSuccessful) {
                        onSuccess()  // Chama a função onSuccess caso a exclusão tenha sucesso
                    } else {
                        Log.e("deletar", "Erro na resposta: ${response.code()}")
                    }
                } catch (e: Exception) {
                    Log.e("deletar", "Erro ao deletar dados do cliente", e)
                }
            }
        } ?: Log.e("deletar perfil", "userId é nulo")
    }

    // Atualize a função updateDadosCliente para aceitar um callback
    fun updateDadosCliente(dadosCliente: DadosCliente, onSuccess: () -> Unit) {
        val clienteId = dadosCliente.id ?: return // Retorna se id for nulo para evitar a chamada com valor inválido

        viewModelScope.launch {
            try {
                val response = apiDadosCliente.updateDadosCliente(clienteId, dadosCliente)
                if (response.isSuccessful) {
                    UserLoginSession.email = dadosCliente.email
                    Log.i("update dados cliente", "Dados do cliente atualizados com sucesso!")
                    onSuccess() // Chama o callback apenas no sucesso
                } else {
                    Log.e("update dados cliente", "Falha na atualização: Código de resposta ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("update dados cliente", "Erro ao realizar update dos dados do cliente", e)
            }
        }
    }


}

