package com.example.mykotlinapp

import Reservation
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReservationDetailsActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_details)

        val reservation = intent.getParcelableExtra<Reservation>("reservation")

        val reservationTitleTextView: TextView = findViewById(R.id.reservationTitleTextView)
        val datesTextView: TextView = findViewById(R.id.datesTextView)
        val arrivalTextView: TextView = findViewById(R.id.arrivalTextView)
        val departureTextView: TextView = findViewById(R.id.departureTextView)


        val instructionsTextView: TextView = findViewById(R.id.instructionsTextView)
        val viewDetailsTextView: TextView = findViewById(R.id.viewDetailsTextView)

        val addressTextView: TextView = findViewById(R.id.addressTextView)
        val getDirectionsTextView: TextView = findViewById(R.id.getDirectionsTextView)


        if (reservation != null) {
            reservationTitleTextView.text = "Reservation pour ${reservation.nombre_de_personne} personnes"
            datesTextView.text = "${reservation.start_time} - ${reservation.end_time}"
            arrivalTextView.text = "Arrivée : 14:00 - 00:00"
            departureTextView.text = "Départ : jusqu'à 11:00"


            instructionsTextView.text = "Votre hôte a fourni des informations sur la façon d'accéder à l'hébergement."
            viewDetailsTextView.text = "Voir les détails"

            addressTextView.text = "${reservation.address}"
            getDirectionsTextView.text = "Obtenir l'itinéraire"

        }
    }
}
