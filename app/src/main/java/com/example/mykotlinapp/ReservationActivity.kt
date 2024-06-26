package com.example.mykotlinapp

import ApiClient
import ApiService
import Reservation
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReservationActivity : AppCompatActivity() {

    private lateinit var reservationsRecyclerView: RecyclerView
    private lateinit var reservationsAdapter: ReservationsAdapter
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        reservationsRecyclerView = findViewById(R.id.reservationsRecyclerView)
        reservationsRecyclerView.layoutManager = LinearLayoutManager(this)

        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_messages -> {
                    val intent = Intent(this, MessagesActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_reservations -> {
                    // Déjà sur la page des réservations
                    true
                }
                R.id.navigation_search -> {
                    val intent = Intent(this, SearchActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_favorites -> {
                    val intent = Intent(this, FavoritesActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        loadReservations()
    }

    private fun loadReservations() {
        val apiService = ApiClient.retrofit.create(ApiService::class.java)
        val sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE)
        val token = sharedPreferences.getString("TOKEN", "") ?: ""

        val call = apiService.getReservationsForUser("Bearer $token")
        call.enqueue(object : Callback<List<Reservation>> {
            override fun onResponse(call: Call<List<Reservation>>, response: Response<List<Reservation>>) {
                if (response.isSuccessful) {
                    val reservations = response.body() ?: emptyList()
                    reservationsAdapter = ReservationsAdapter(reservations) { reservation ->
                        val intent = Intent(this@ReservationActivity, ReservationDetailsActivity::class.java)
                        intent.putExtra("reservation", reservation)
                        startActivity(intent)
                    }
                    reservationsRecyclerView.adapter = reservationsAdapter
                } else {
                    Toast.makeText(this@ReservationActivity, "Failed to load reservations", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Reservation>>, t: Throwable) {
                Toast.makeText(this@ReservationActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
