
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface ApiService {
    @POST("/api/mobile/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>




    @GET("/api/mobile/reservations/user")
    fun getReservationsForUser(@Header("Authorization") token: String): Call<List<Reservation>>
}
