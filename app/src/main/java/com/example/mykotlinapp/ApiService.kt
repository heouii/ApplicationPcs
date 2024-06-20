import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class LoginRequest(val username: String, val password: String)

interface ApiService {
    @POST("login")
    fun login(@Body request: LoginRequest): Call<User>
}
