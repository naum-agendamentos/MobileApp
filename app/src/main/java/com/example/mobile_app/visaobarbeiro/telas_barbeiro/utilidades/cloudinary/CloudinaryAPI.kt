import com.example.mobile_app.visaobarbeiro.telas_barbeiro.utilidades.cloudinary.CloudinaryResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface CloudinaryApi {
    @Multipart
    @POST("v1_1/dmgfyyioo/image/upload")
    fun uploadImage(
        @Part file: MultipartBody.Part,
        @Part("upload_preset") uploadPreset: String
    ): Call<CloudinaryResponse>
}
