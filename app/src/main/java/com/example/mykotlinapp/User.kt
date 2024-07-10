package com.example.mykotlinapp.model

data class LoginResponse(
    val access_token: String,
    val token_type: String,
    val user: User
)

data class User(
    val name: String,
    val email: String,
    val first_name: String,
    val created_at: String,
    val subscriptionName: String?,
    val freeServicesRemaining: Int?,
    val nextFreeServiceTime: String?
)


data class LoginRequest(
    val email: String,
     val password: String
)

