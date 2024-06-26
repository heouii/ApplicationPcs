data class User(

    val name: String,
    val first_name:String,
    val email: String,
    val created_at: String,

)

data class LoginResponse(
    val access_token: String,
    val token_type: String,
    val user: User
)

data class LoginRequest(
    val email: String,
     val password: String
)

