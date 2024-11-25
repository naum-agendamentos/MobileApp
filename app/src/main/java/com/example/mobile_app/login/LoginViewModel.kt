import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.mobile_app.login.ApiLogin
import com.example.mobile_app.login.DadosPorId
import com.example.mobile_app.login.NovaSession
import com.example.mobile_app.login.SessionLogin
import com.example.mobile_app.login.UserLoginSession
import com.example.mobile_app.visaobarbeiro.api.RetrofitService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// LoginViewModel.kt
// LoginViewModel.kt
class LoginViewModel:ViewModel() {
    private val apiLogin: ApiLogin = RetrofitService.apiLogin
    private val apiDadosPorId : DadosPorId = RetrofitService.apiDadosPorId
    var sessionLogin = SessionLogin()
    suspend fun logar(novaSession: NovaSession): SessionLogin? {
        return if (!novaSession.email.isNullOrBlank() && !novaSession.senha.isNullOrBlank()) {
            try {
                val response = apiLogin.login(novaSession)
                if (response.isSuccessful) {
                    val usuario = response.body()
                    usuario?.token?.let {
                        RetrofitService.updateToken(it)
                        UserLoginSession.userId = usuario.userId
                        UserLoginSession.token = usuario.token
                        UserLoginSession.email = usuario.email
                        UserLoginSession.senha = novaSession.senha
                        if(usuario != null && usuario.tipo == "CLIENTE"){
                            carregarDadosCliente()
                        }
                        else if(usuario != null && usuario.tipo == "BARBEIRO"){
                            carregarDadosBarbeiro()
                        }
                        return usuario // Login bem-sucedido
                    } ?: return null // Falha se o token for nulo
                } else {
                    Log.e("api Login", "Erro na resposta do login: ${response.code()}")
                    return null // Falha na resposta
                }
            } catch (e: Exception) {
                Log.e("api Login", "Erro ao fazer login", e)
                return null // Falha na execução
            }
        } else {
            Log.e("api Login", "Valores em Branco")
            return null // Falha por campos em branco
        }
    }


    private fun carregarDadosCliente() {
        UserLoginSession.userId?.let { userId ->
            viewModelScope.launch {
                try {
                    val response = apiDadosPorId.getDadosCliente(userId)
                    Log.d("carregar perfil", "ID: ${UserLoginSession.userId}")
                    Log.d("carregar perfil", "Resposta da API: $response")
                    if (response.isSuccessful) {
                         val clienteId = response.body()?.id
                        if (clienteId != null) {
                            UserLoginSession.idCliente = clienteId
                            Log.d("carregar perfil", "ID do Cliente: $clienteId")
                        } else {
                            Log.e("carregar perfil", "O corpo da resposta está vazio ou ID não encontrado")
                        }

                    } else {
                        Log.e("carregar perfil", "Erro na resposta: ${response.code()}")
                    }
                } catch (e: Exception) {
                    Log.e("carregar perfil", "Erro ao carregar dados do cliente", e)
                }
            }
        } ?: Log.e("carregar perfil", "userId é nulo")
    }

    private fun carregarDadosBarbeiro() {
        UserLoginSession.userId?.let { userId ->
            viewModelScope.launch {
                try {
                    val response = apiDadosPorId.getDadosBarbeiros(userId)
                    Log.d("carregar perfil", "ID: ${UserLoginSession.userId}")
                    Log.d("carregar perfil", "Resposta da API: $response")
                    if (response.isSuccessful) {
                        val barbeiroId = response.body()?.id
                        if (barbeiroId != null) {
                            UserLoginSession.idBarbeiro = barbeiroId
                            Log.d("carregar perfil", "ID do Barbeiro: $barbeiroId")
                        } else {
                            Log.e("carregar perfil", "O corpo da resposta está vazio ou ID não encontrado")
                        }

                    } else {
                        Log.e("carregar perfil", "Erro na resposta: ${response.code()}")
                    }
                } catch (e: Exception) {
                    Log.e("carregar perfil", "Erro ao carregar dados do cliente", e)
                }
            }
        } ?: Log.e("carregar perfil", "userId é nulo")
    }
}


