package com.example.mykotlinapp

import Reservation
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReservationsAdapter(
    private val reservations: List<Reservation>,
    private val onItemClick: (Reservation) -> Unit
) : RecyclerView.Adapter<ReservationsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val reservationTitleTextView: TextView = view.findViewById(R.id.reservationTitleTextView)
        val datesTextView: TextView = view.findViewById(R.id.datesTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_reservation_details, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reservation = reservations[position]
        holder.reservationTitleTextView.text = "Reservation for ${reservation.nombre_de_personne} persons"
        holder.datesTextView.text = "${reservation.start_time} - ${reservation.end_time}"
        holder.itemView.setOnClickListener { onItemClick(reservation) }
    }

    override fun getItemCount() = reservations.size
}
