package com.example.mykotlinapp

import ApiClient
import ApiService
import Reservation
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReservationActivity : AppCompatActivity() {

    private lateinit var reservationsTextView: TextView
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        reservationsTextView = findViewById(R.id.reservationsTextView)

        apiService = ApiClient.retrofit.create(ApiService::class.java)


        val sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("TOKEN", "") ?: ""

        if (token.isNotEmpty()) {
            loadReservations("Bearer $token")
        } else {
            Toast.makeText(this, "Token is empty or null", Toast.LENGTH_LONG).show()
        }
    }

    private fun loadReservations(token: String) {
        val call = apiService.getReservationsForUser(token)

        call.enqueue(object : Callback<List<Reservation>> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<List<Reservation>>, response: Response<List<Reservation>>) {
                if (response.isSuccessful) {
                    val reservations = response.body() ?: emptyList()
                    displayReservations(reservations)
                } else {
                    reservationsTextView.text = "Failed to load reservations: ${response.message()}"
                }
            }

            @SuppressLint("SetTextI18n")
            override fun onFailure(call: Call<List<Reservation>>, t: Throwable) {
                reservationsTextView.text = "API Error: ${t.message}"
            }
        })
    }

    private fun displayReservations(reservations: List<Reservation>) {
        val stringBuilder = StringBuilder()
        for (reservation in reservations) {
            stringBuilder.append("Reservation for ${reservation.nombre_de_personne} persons\n")
            stringBuilder.append("From ${reservation.start_time} to ${reservation.end_time}\n")
            stringBuilder.append("Apartment ID: ${reservation.appartement_id}\n")
            stringBuilder.append("Price: ${reservation.prix}\n\n")
        }
        reservationsTextView.text = stringBuilder.toString()
    }
}


