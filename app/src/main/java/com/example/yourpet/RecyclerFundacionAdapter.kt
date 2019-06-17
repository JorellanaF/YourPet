package com.example.yourpet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.cardview_fundacion.*
import kotlinx.android.synthetic.main.cardview_fundacion.view.*


class RecyclerFundacionAdapter(var fundaciones: List<Fundacion>): RecyclerView.Adapter<RecyclerFundacionAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_fundacion, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = fundaciones.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int): Unit = holder.bind(fundaciones[position])

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(item: Fundacion) = with(itemView){

            Glide.with(itemView.context)
                .load(item.imagen)
                .placeholder(R.drawable.ic_launcher_background)
                .into(iv_logo)

            tv_nombre.text = item.nombre_fundacion
        }
    }
}