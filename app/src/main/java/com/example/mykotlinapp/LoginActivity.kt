package com.example.mykotlinapp

import ApiClient
import ApiService
import LoginRequest
import User
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usernameEditText = findViewById(R.id.MailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            login(username, password)
        }
    }

    private fun login(username: String, password: String) {
        val apiService = ApiClient.retrofit.create(ApiService::class.java)
        val call = apiService.login(LoginRequest(username, password))

        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    Toast.makeText(this@LoginActivity, "Login Successful: ${user?.name}", Toast.LENGTH_LONG).show()

                } else {
                    Toast.makeText(this@LoginActivity, "Login Failed: ${response.message()}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "API Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}
