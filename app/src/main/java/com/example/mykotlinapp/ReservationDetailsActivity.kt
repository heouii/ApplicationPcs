package com.example.mykotlinapp

import Reservation
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ReservationDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_details)

        val reservation = intent.getParcelableExtra<Reservation>("reservation")

        val reservationTitleTextView: TextView = findViewById(R.id.reservationTitleTextView)
        val datesTextView: TextView = findViewById(R.id.datesTextView)
        val propertyTypeTextView: TextView = findViewById(R.id.propertyTypeTextView)
        val addressTextView: TextView = findViewById(R.id.addressTextView)
        val cityTextView: TextView = findViewById(R.id.cityTextView)
        val postalCodeTextView: TextView = findViewById(R.id.postalCodeTextView)
        val priceTextView: TextView = findViewById(R.id.priceTextView)
        val arrivalTextView: TextView = findViewById(R.id.arrivalTextView)
        val departureTextView: TextView = findViewById(R.id.departureTextView)
        val instructionsTextView: TextView = findViewById(R.id.instructionsTextView)
        val viewDetailsTextView: TextView = findViewById(R.id.viewDetailsTextView)
        val getDirectionsTextView: TextView = findViewById(R.id.getDirectionsTextView)

        if (reservation != null) {
            reservationTitleTextView.text = "Réservation pour ${reservation.nombre_de_personne} personnes"
            datesTextView.text = "${reservation.start_time} - ${reservation.end_time}"
            propertyTypeTextView.text = "Type de propriété: ${reservation.property_type}"
            addressTextView.text = reservation.appartement.address
            cityTextView.text = "Ville: ${reservation.appartement.city}"
            postalCodeTextView.text = "Code Postal: ${reservation.appartement.postal_code}"
            priceTextView.text = "Prix: ${reservation.prix}€"
            arrivalTextView.text = "Arrivée : 14:00 - 00:00"
            departureTextView.text = "Départ : jusqu'à 11:00"

            getDirectionsTextView.text = "Obtenir l'itinéraire"

            getDirectionsTextView.setOnClickListener {
                Log.d("ReservationDetails", "Get Directions text view clicked")
                openGoogleMapsWeb("${reservation.appartement.address}, ${reservation.appartement.city}, ${reservation.appartement.postal_code}")
            }
        }
    }

    private fun openGoogleMapsWeb(address: String) {
        Log.d("ReservationDetails", "Opening Google Maps web with address: $address")
        val encodedAddress = Uri.encode(address)
        val uri = Uri.parse("https://www.google.com/maps/search/?api=1&query=$encodedAddress")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}
