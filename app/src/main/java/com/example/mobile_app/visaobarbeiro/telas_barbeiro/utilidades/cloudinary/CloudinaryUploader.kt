import android.net.Uri
import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

@Composable
fun CloudinaryUploader(
    imageUri: Uri?,
    onUploadComplete: (String) -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var uploadUrl by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(imageUri) {
        imageUri?.let { uri ->
            val file = File(uri.path!!)
            val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
            val body = MultipartBody.Part.createFormData("file", file.name, requestBody)

            coroutineScope.launch {
                try {
                    val response = withContext(Dispatchers.IO) {
                        RetrofitInstance.api.uploadImage(body, "fdl9l3yj").execute()
                    }

                    if (response.isSuccessful) {
                        response.body()?.secureUrl?.let { url ->
                            uploadUrl = url
                            onUploadComplete(url)
                        }
                    } else {
                        Log.e("CloudinaryUploader", "Error: ${response.errorBody()?.string()}")
                    }
                } catch (e: Exception) {
                    Log.e("CloudinaryUploader", "Exception: ${e.message}", e)
                }
            }
        }
    }

    uploadUrl?.let { url ->
        Text(text = url)
    }
}
