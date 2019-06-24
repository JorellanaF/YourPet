package com.example.yourpet.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.cardview_fundacion.view.*
import com.example.yourpet.Fundacion
import com.example.yourpet.R


class RecyclerFundacionAdapter(var fundaciones: List<Fundacion>, listener: OnItemClickListener):
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
        private var listener1: OnItemClickListener? = null
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

        fun bind(item: Fundacion) = with(itemView) {

            Glide.with(itemView.context)
                .load(item.imagen)
                .placeholder(R.drawable.ic_launcher_background)
                .into(iv_logo)

            tv_nombre.text = item.nombre_fundacion
        }
    }
}