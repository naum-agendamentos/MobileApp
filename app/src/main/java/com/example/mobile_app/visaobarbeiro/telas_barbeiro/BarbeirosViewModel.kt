package com.example.mobile_app.visaobarbeiro.telas_barbeiro

import android.content.ContentResolver
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_app.visaobarbeiro.api.RetrofitService
//import com.example.mobile_app.visaobarbeiro.telas_barbeiro.bloqueio_de_dia.componente.SemanaEntity
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.cadastrar_barbeiro.componente.CadastrarBarbeiro
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.editar_barbeiro.componente.EditarBarbeiro
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.ver_barbeiro.componente.VerBarbeiro
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okio.BufferedSink
import okio.source
import org.json.JSONObject
import java.io.InputStream

class BarbeirosViewModel : ViewModel() {
    private val _barbeiros = mutableStateListOf<VerBarbeiro>()
    val barbeiros: List<VerBarbeiro> get() = _barbeiros

    var isLoading = mutableStateOf(true)
        private set

    var isSubmitting = mutableStateOf(false)
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set

    private val apiBarbeiro: ApiBarbeiros = RetrofitService.apiBarbeiros

    init {
        fetchBarbeiros()
    }

    fun fetchBarbeiros() {
        isLoading.value = true // Define loading como true antes da chamada
        viewModelScope.launch {
            try {
                val resposta = apiBarbeiro.get()
                Log.i("api", "Resposta da API: ${resposta.body()}")
                if (resposta.isSuccessful) {
                    resposta.body()?.let {
                        _barbeiros.clear()
                        _barbeiros.addAll(it)
                        Log.i("api", "Barbeiros carregados com sucesso: ${_barbeiros.size} barbeiros encontrados.")
                    }
                } else {
                    errorMessage.value = resposta.errorBody()?.string()
                    Log.e("api", "Erro ao buscar barbeiros: ${errorMessage.value}")
                }
            } catch (exception: Exception) {
                Log.e("api", "Erro ao buscar barbeiros: ${exception.message}")
                errorMessage.value = exception.message // Armazena mensagem de erro
            } finally {
                isLoading.value = false
            }
        }
    }

    fun cadastrarBarbeiro(novoBarbeiro: CadastrarBarbeiro) {
        viewModelScope.launch {
            isSubmitting.value = true
            try {
                val resposta = apiBarbeiro.cadastrar(novoBarbeiro)
                Log.i("api", "Resposta da API: ${resposta.body()}")
                if (resposta.isSuccessful) {
                    resposta.body()?.let {
                        _barbeiros.add(it)
                    }
                } else {
                    Log.e("api", "Erro ao cadastrar barbeiro: ${resposta.errorBody()?.string()}")
                }
            } catch (exception: Exception) {
                Log.e("api", "Erro ao cadastrar barbeiro: ${exception}")
            } finally {
                isSubmitting.value = false
            }
        }
    }

    fun editarBarbeiro(barbeiroId: Long, editarBarbeiro: EditarBarbeiro) {
        viewModelScope.launch {
            Log.d("ViewModel", "Tentando editar barbeiro com ID: $barbeiroId")
            isSubmitting.value = true
            try {
                val resposta = apiBarbeiro.editar(barbeiroId, editarBarbeiro)
                Log.i("api", "Resposta da API: ${resposta.body()}")
                if (resposta.isSuccessful) {
                    Log.d("ViewModel", "Barbeiro editado com sucesso!")
                    resposta.body()?.let {
                        // Atualiza a lista de barbeiros com o barbeiro editado
                        val index = _barbeiros.indexOfFirst { it.id == it.id } // Compare com it.id
                        if (index != -1) {
                            _barbeiros[index] = it // Atualiza o barbeiro existente
                        } else {
                            _barbeiros.add(it) // Adiciona um novo barbeiro caso não exista
                        }
                    }
                } else {
                    Log.e("api", "Erro ao editar barbeiro: ${resposta.errorBody()?.string()}")
                }
            } catch (exception: Exception) {
                Log.e("api", "Erro ao editar barbeiro: ${exception}")
            } finally {
                isSubmitting.value = false
            }
        }
    }

    fun uploadImageToCloudinary(imageUri: Uri, contentResolver: ContentResolver, onUploadComplete: (String?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Obtém o InputStream do URI
                val inputStream: InputStream? = contentResolver.openInputStream(imageUri)
                if (inputStream == null) {
                    Log.e("Upload", "InputStream is null")
                    onUploadComplete(null)
                    return@launch
                }

                val fileName = imageUri.lastPathSegment ?: "image.jpg"

                // Cria o RequestBody para o arquivo da imagem
                val requestFile = object : RequestBody() {
                    override fun contentType(): MediaType? {
                        return "image/jpeg".toMediaTypeOrNull() // ou outro tipo de imagem
                    }

                    override fun writeTo(sink: BufferedSink) {
                        sink.writeAll(inputStream.source())
                        inputStream.close() // Feche o InputStream após a escrita
                    }
                }

                // Cria o MultipartBody para enviar o arquivo e o upload preset
                val requestBody = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("file", fileName, requestFile)
                    .addFormDataPart("upload_preset", "fdl9l3yj") // Seu upload preset
                    .build()

                val request = Request.Builder()
                    .url("https://api.cloudinary.com/v1_1/dmgfyyioo/image/upload")
                    .post(requestBody)
                    .build()

                val client = OkHttpClient()
                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    val responseBody = response.body?.string()
                    val jsonObject = JSONObject(responseBody)
                    val secureUrl = jsonObject.getString("secure_url") // Obtém a URL segura
                    onUploadComplete(secureUrl)
                } else {
                    Log.e("Upload", "Erro no upload: ${response.message}")
                    onUploadComplete(null)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                onUploadComplete(null)
            }
        }
    }

//    fun atualizarSemanaBarbeiro(barbeiroId: Long, novaSemana: SemanaEntity) {
//        viewModelScope.launch {
//            Log.d("ViewModel", "Tentando editar semana do barbeiro com ID: $barbeiroId")
//            isSubmitting.value = true
//            try {
//                var response = apiBarbeiro.putBarbeiros(barbeiroId, novaSemana)
//                if (response.isSuccessful)
//                    Log.e("Sucesso!", "Response: ${response.body()}")
//            } catch (exception: Exception) {
//                Log.e("api", "Erro ao editar barbeiro: ${exception}")
//            } finally {
//                isSubmitting.value = false
//            }
//        }
//    }


}

