import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.mobile_app.login.ApiLogin
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
}


