import LoginViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_app.login.UserLoginSession
import kotlinx.coroutines.launch
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.mobile_app.visaobarbeiro.api.RetrofitService
import com.example.mobile_app.visaocliente.componentes.meuperfilcomponentes.ApiDadosCliente
import com.example.mobile_app.visaocliente.componentes.meuperfilcomponentes.DadosCliente
import kotlinx.coroutines.GlobalScope

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
                        UserLoginSession.email = dadosCliente.value?.email

                    } else {
                        Log.e("carregar perfil", "Erro na resposta: ${response.code()}")
                    }
                } catch (e: Exception) {
                    Log.e("carregar perfil", "Erro ao carregar dados do cliente", e)
                }
            }
        } ?: Log.e("carregar perfil", "userId é nulo")
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


    fun excluirConta(onSuccess: () -> Unit) {
        val clienteId = dadosCliente.value?.id ?: return // Retorna se id for nulo
        Log.i("excluir conta", "ID do cliente: $clienteId")
        viewModelScope.launch {
            try{
                var respose = apiDadosCliente.deleteCliente(clienteId)
                if(respose.isSuccessful){
                    Log.i("excluir conta", "Dados do cliente excluídos com sucesso!")
                    onSuccess()
                }
            }catch (e: Exception){
                Log.e("excluir conta", "Erro ao realizar exclusão dos dados do cliente", e)
            }

        }
    }


    fun updateDadosClienteSenha(dadosCliente: DadosCliente, onSuccess: () -> Unit, senhaAtual: String,senhaNova: String,confirmacaoSenha: String, senhaState: String) {
        val clienteId = dadosCliente.id ?: return // Retorna se id for nulo para evitar a chamada com valor inválido
        Log.i("Senha","Senha atual: $senhaAtual")
        Log.i("Senha","Senha atualDado: ${dadosCliente.senha}")
        Log.i("Senha","Senha Nova: $senhaNova")
        Log.i("Senha","Senha confirma: $confirmacaoSenha")
        if(senhaAtual == senhaState && senhaNova == confirmacaoSenha){
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

        }else{
            Log.e("update dados cliente", "Dados de senha inválidos")
        }

    }
}

