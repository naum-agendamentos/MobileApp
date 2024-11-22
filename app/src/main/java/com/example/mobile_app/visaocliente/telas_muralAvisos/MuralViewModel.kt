import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import android.util.Log
import com.example.homepage.visaocliente.componentes.muralcomponentes.Aviso
import com.example.mobile_app.visaobarbeiro.api.RetrofitService
import com.example.mobile_app.visaocliente.telas_muralAvisos.ApiMural

class MuralViewModel : ViewModel() {
    private val apiMural: ApiMural = RetrofitService.getApiAviso
    var avisos = mutableStateListOf<Aviso>()
        private set

    var itemAtual by mutableStateOf(Aviso())

    init {
        fetchAvisos()
    }

    private fun fetchAvisos() {
        viewModelScope.launch {
            try {
                val resposta = apiMural.get()
                Log.i("api", "Resposta da api ${resposta.body()}")
                if (resposta.isSuccessful) {
                    Log.i("api", "items da API: ${resposta.body()}")
                    resposta.body()?.let { newAvisos ->
                        avisos.clear()
                        avisos.addAll(newAvisos)
                    }
                } else {
                    Log.e("api", "Erro ao buscar items: ${resposta.errorBody()?.string()}")
                }
            } catch (exception: Exception) {
                Log.e("api", "Erro ao buscar items: ,${exception}")
            }
        }
    }

    fun salvar() {
        viewModelScope.launch {
            try {
                val resposta = if (isNovo()) {
                    apiMural.post(itemAtual)
                } else {
                    apiMural.put(itemAtual.id!!, itemAtual)
                }

                if (resposta.isSuccessful) {
                    resposta.body()?.let { savedAviso ->
                        if (isNovo()) {
                            avisos.add(savedAviso)
                        } else {
                            val posicaoDoAtual = avisos.indexOfFirst { it.id == itemAtual.id }
                            if (posicaoDoAtual >= 0) {
                                avisos[posicaoDoAtual] = savedAviso
                            } else {

                            }
                        }
                    }
                } else {
                    Log.e("api", "Erro ao salvar aviso: ${resposta.errorBody()?.string()}")
                }
            } catch (exception: Exception) {
                Log.e("api", "Erro ao salvar aviso", exception)
            }
        }
    }

    fun isNovo() = itemAtual.id == null

    fun setItemParaEdicao(aviso: Aviso) {
        itemAtual = aviso
    }
}
