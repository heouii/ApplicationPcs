package com.example.mykotlinapp

import ApiClient
import ApiService
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mykotlinapp.model.LoginRequest
import com.example.mykotlinapp.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            login(email, password)
        }
    }

    private fun login(email: String, password: String) {
        val apiService = ApiClient.retrofit.create(ApiService::class.java)
        val call = apiService.login(LoginRequest(email, password))

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    Toast.makeText(this@LoginActivity, "Login Successful: ${loginResponse?.user?.name}", Toast.LENGTH_LONG).show()
                    val token = loginResponse?.access_token

                    val sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putString("TOKEN", token)

                    loginResponse?.user?.let { user ->
                        editor.putString("USER_NAME", user.name)
                        editor.putString("USER_EMAIL", user.email)
                        editor.putString("USER_FIRST_NAME", user.first_name)
                        editor.putString("USER_CREATED_AT", user.created_at)
                        editor.putString("USER_SUBSCRIPTION_NAME", user.subscriptionName)
                        editor.putInt("USER_FREE_SERVICES_REMAINING", user.freeServicesRemaining ?: 0)
                        editor.putString("USER_NEXT_FREE_SERVICE_TIME", user.nextFreeServiceTime)
                    }
                    editor.apply()

                    val intent = Intent(this@LoginActivity, ReservationActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@LoginActivity, "Login Failed: ${response.message()}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "API Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}
