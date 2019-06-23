package com.example.yourpet

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yourpet.Fragments.details_fundacion
import com.example.yourpet.Fragments.fundaciones
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_detalles_fundacion.*
import kotlinx.android.synthetic.main.cardview_fundacion.*
import kotlinx.android.synthetic.main.cardview_fundacion.view.*
import kotlinx.android.synthetic.main.fragment_details_fundacion.view.*
import android.widget.AdapterView.OnItemClickListener




class RecyclerFundacionAdapter(var fundaciones: List<Fundacion>, listener:OnItemClickListener):
    RecyclerView.Adapter<RecyclerFundacionAdapter.ViewHolder>() {

    init{
        listener1 = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_fundacion, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = fundaciones.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(fundaciones[position])
        holder.setOnClickListener()
    }

    interface OnItemClickListener {
        fun onItemClickListener(view:View)
    }

    companion object {
        private var listener1:OnItemClickListener? = null
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        override fun onClick(v: View?) {
            if (v != null) {
                listener1?.onItemClickListener(v)
            }
        }

        var context: Context = itemView.context

        fun setOnClickListener() {
            itemView.setOnClickListener(this)
        }

            /*var intent = Intent(context, detallesFundacion::class.java)
            intent.putExtra("nombre", v.tv_nombre.text.toString())
            Log.d("recibido", intent.putExtra("imagen", v.tv_nombre.text.toString()).toString())
            context.startActivity(intent)*/

            //Log.d("presionado", "HAYYY " + v.tv_nombre.text)


        fun bind(item: Fundacion) = with(itemView) {

            Glide.with(itemView.context)
                .load(item.imagen)
                .placeholder(R.drawable.ic_launcher_background)
                .into(iv_logo)

            tv_nombre.text = item.nombre_fundacion
        }
    }
}