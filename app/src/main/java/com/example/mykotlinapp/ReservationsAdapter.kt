package com.example.mykotlinapp

import Reservation
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReservationsAdapter(
    private val reservations: List<Reservation>
) : RecyclerView.Adapter<ReservationsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val reservationNameTextView: TextView = view.findViewById(R.id.reservationNameTextView)
        val reservationDetailsTextView: TextView = view.findViewById(R.id.reservationDetailsTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_reservation, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reservation = reservations[position]
        holder.reservationNameTextView.text = "Reservation for ${reservation.nombre_de_personne} persons"
        holder.reservationDetailsTextView.text = "From ${reservation.start_time} to ${reservation.end_time}"
    }

    override fun getItemCount() = reservations.size
}
