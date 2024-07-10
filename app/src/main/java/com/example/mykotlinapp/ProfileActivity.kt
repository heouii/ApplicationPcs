package com.example.mykotlinapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity() {

    private lateinit var nameTextView: TextView
    private lateinit var first_NameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var creatTextView: TextView
    private lateinit var subscriptionNameTextView: TextView
    private lateinit var logoutButton: Button
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        nameTextView = findViewById(R.id.nameTextView)
        first_NameTextView = findViewById(R.id.first_NameTextView)
        emailTextView = findViewById(R.id.emailTextView)
        creatTextView = findViewById(R.id.creatTextView)
        subscriptionNameTextView = findViewById(R.id.subscriptionNameTextView)

        logoutButton = findViewById(R.id.logoutButton)

        val sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val userName = sharedPreferences.getString("USER_NAME", "N/A")
        val userEmail = sharedPreferences.getString("USER_EMAIL", "N/A")
        val userFirstName = sharedPreferences.getString("USER_FIRST_NAME", "N/A")
        val userCreatedAt = sharedPreferences.getString("USER_CREATED_AT", "N/A")
        val subscriptionName = sharedPreferences.getString("USER_SUBSCRIPTION_NAME", "N/A")
        val nextFreeServiceTime = sharedPreferences.getString("USER_NEXT_FREE_SERVICE_TIME", "N/A")

        nameTextView.text = userName
        first_NameTextView.text = userFirstName
        emailTextView.text = userEmail
        creatTextView.text = userCreatedAt
        subscriptionNameTextView.text = subscriptionName

        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
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
                R.id.navigation_reservations -> {
                    val intent = Intent(this, ReservationActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_messages -> {
                    val intent = Intent(this, MessagesActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_profile -> {
                    // Already in profile activity
                    true
                }
                else -> false
            }
        }

        bottomNavigationView.selectedItemId = R.id.navigation_profile

        logoutButton.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        val sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            clear()
            apply()
        }
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
