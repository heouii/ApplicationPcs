package com.example.mykotlinapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val logoutButton: Button = findViewById(R.id.logoutButton)
        logoutButton.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        val  myReservationButton : Button = findViewById(R.id.myReservationsButton)
        myReservationButton.setOnClickListener{

            val token = "auth_token"

            val intent =Intent( this,ReservationActivity::class.java)
            intent.putExtra("TOKEN", token)
            startActivity(intent)

        }
    }
}